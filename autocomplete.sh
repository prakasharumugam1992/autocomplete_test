#! /bin/bash

usage() {
    echo "usage:"
    echo " ${0} [log file] (run the program with log file as input)"
    echo " log file - either sample_query.log(for debugging) or query.log(actual)"
    exit 1
}

if [ "$#" -lt "1" ]; then
    usage
fi

./gradlew clean assemble
LIB_JARS=`find -H lib -name "*.jar" | tr "\n" ":"`

java -cp $LIB_JARS:build/classes/java/main com.govtech.pic.suggest.Suggester $1
