#!/usr/bin/env bash
#
set -e
#
URL=http://localhost:8080/rest/todo
NUM_REQUEST=100000
CONCURRENCY=32
THREADS=4
#
touch result.log || rm result.log && touch result.log
echo " " >> result.log 2>&1
echo "========================================================== " >> result.log 2>&1
echo " " >> result.log 2>&1
echo "====================WARM-UP======================== " >> result.log 2>&1
echo "============`date` Warming up the API with ${NUM_REQUEST} requests" >> result.log 2>&1

echo " " >> result.log 2>&1
h2load -n$NUM_REQUEST -c1 --h1 $URL >> result.log 2>&1
echo " " >> result.log 2>&1


echo "============`date` API warmed up. " >> result.log 2>&1
echo " " >> result.log 2>&1
echo "====================END WARM-UP======================== " >> result.log 2>&1
echo "=== Sleeping for 2s" >> result.log 2>&1
sleep 2
echo "====================BENCHMARK======================== " >> result.log 2>&1

echo "============`date` Running load test ..." >> result.log 2>&1
#The benchmark
h2load -n$NUM_REQUEST -c$CONCURRENCY -t$THREADS --h1 $URL >> result.log 2>&1
echo " " >> result.log 2>&1
echo "============`date` Done ..." >> result.log 2>&1
echo " " >> result.log 2>&1
echo "====================END BENCHMARK======================== " >> result.log 2>&1
echo " " >> result.log 2>&1
echo " " >> result.log 2>&1
echo " " >> result.log 2>&1
echo " " >> result.log 2>&1
