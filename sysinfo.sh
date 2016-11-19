#!/usr/bin/env bash
echo "===CPU:"
cat /proc/cpuinfo |  egrep "model name|cores"
echo " "
echo "===RAM: "
free -h

printf "\n"

echo "===Java version: "
java -version
echo " "
echo "===OS: "
uname -a

printf "\n"

echo "===Node: "
node --version

printf "\n"

echo "=== Go: "
go version
