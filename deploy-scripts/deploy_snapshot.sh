#!/usr/bin/env bash

VERSION_PATTERN=^[0-9]+\.[0-9]+\.[0-9]+-SNAPSHOT$

echo "Deploying snapshot '$VERSION_NAME' ..."

if [ "$TRAVIS_REPO_SLUG" != "$SLUG" ]; then
  fail "Failed snapshot deployment: wrong repository. Expected '$SLUG' but was '$TRAVIS_REPO_SLUG'."
elif [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
  fail "Failed snapshot deployment: was pull request."
elif [ "$TRAVIS_BRANCH" != "$BRANCH" ]; then
  fail "Failed snapshot deployment: wrong branch. Expected '$BRANCH' but was '$TRAVIS_BRANCH'."
elif ! [[ "$VERSION_NAME" =~ $VERSION_PATTERN ]]; then
  echo "Skipping snapshot deployment '$VERSION_NAME': This is probably a pre-release build"
else
  ./gradlew publish
  echo "Snapshot '$VERSION_NAME' deployed!"
fi
