#!/bin/bash
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# Apache Maven Wrapper startup batch script, version 3.3.2
#
# Optional ENV vars
# -----------------
#   JAVA_HOME - location of a JDK home dir, required when downloading Maven via Java source
#   MVNW_REPOURL - repo URL base for downloading Maven distribution
#   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading Maven
#   MVNW_VERBOSE - true: enable verbose log; debug: trace the mvnw script; others: silence the output

set -euf
[ "${MVNW_VERBOSE-}" != debug ] || set -x

# OS specific support.
native_path() { printf %s\\n "$1"; }
case "$(uname)" in
CYGWIN* | MINGW*)
  [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
  native_path() { cygpath --path --windows "$1"; }
  ;;
esac

# Set JAVA_HOME and validate it
set_java_home() {
  if [ -n "${JAVA_HOME-}" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
      JAVACMD="$JAVA_HOME/jre/sh/java"
      JAVACCMD="$JAVA_HOME/jre/sh/javac"
    else
      JAVACMD="$JAVA_HOME/bin/java"
      JAVACCMD="$JAVA_HOME/bin/javac"
      if [ ! -x "$JAVACMD" ] || [ ! -x "$JAVACCMD" ]; then
        echo "The JAVA_HOME environment variable is not defined correctly." >&2
        return 1
      fi
    fi
  else
    JAVACMD="$(command -v java)" || :
    JAVACCMD="$(command -v javac)" || :
    if [ ! -x "${JAVACMD-}" ] || [ ! -x "${JAVACCMD-}" ]; then
      echo "The java/javac command does not exist in PATH nor is JAVA_HOME set." >&2
      return 1
    fi
  fi
}

# Hash function for Maven Wrapper
hash_string() {
  str="${1:-}" h=0
  while [ -n "$str" ]; do
    char="${str%"${str#?}"}"
    h=$(((h * 31 + $(LC_CTYPE=C printf %d "'$char")) % 4294967296))
    str="${str#?}"
  done
  printf %x\\n $h
}

# Read properties file for distribution URL
while IFS="=" read -r key value; do
  case "${key-}" in
  distributionUrl) distributionUrl=$(echo "${value-}" | tr -d '[:space:]') ;;
  distributionSha256Sum) distributionSha256Sum=$(echo "${value-}" | tr -d '[:space:]') ;;
  esac
done <"${0%/*}/.mvn/wrapper/maven-wrapper.properties"
[ -n "${distributionUrl-}" ] || { echo "Cannot read distributionUrl property"; exit 1; }

# Determine Maven Home directory
MAVEN_USER_HOME="${MAVEN_USER_HOME:-${HOME}/.m2}"
MAVEN_HOME="${MAVEN_USER_HOME}/wrapper/dists/$(basename "${distributionUrl%-bin.*}")/$(hash_string "$distributionUrl")"

# Execute Maven if installed
if [ -d "$MAVEN_HOME" ]; then
  exec "$MAVEN_HOME/bin/mvn" "$@"
fi

# Download and install Maven if not available
mkdir -p "${MAVEN_HOME%/*}"
TMP_DOWNLOAD_DIR=$(mktemp -d) || { echo "Cannot create temp dir"; exit 1; }
trap 'rm -rf -- "$TMP_DOWNLOAD_DIR"' HUP INT TERM EXIT

wget --quiet "$distributionUrl" -O "$TMP_DOWNLOAD_DIR/maven.zip" || {
  echo "Failed to download Maven"; exit 1;
}

unzip -q "$TMP_DOWNLOAD_DIR/maven.zip" -d "$TMP_DOWNLOAD_DIR" || {
  echo "Failed to unzip Maven"; exit 1;
}

mv "$TMP_DOWNLOAD_DIR/$(basename "${distributionUrl%-bin.*}")" "$MAVEN_HOME" || {
  echo "Failed to move Maven directory"; exit 1;
}

exec "$MAVEN_HOME/bin/mvn" "$@"
