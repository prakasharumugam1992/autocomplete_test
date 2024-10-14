Here is the HTML content converted to Markdown:

# README

- [Overview](#overview)
- [Job title autocomplete](#at)
- [What Code to Write](#wctw)
- [Building and Running](#bar)

## OVERVIEW

Autocomplete feature is a very common feature in many search-related websites. As an example, see [mycareersfuture's what autocomplete](images/autocomplete.png).

Your task is to implement part of the "suggest" component for job titles.

## JOB TITLE AUTOCOMPLETE

We have provided a skeleton java program that currently returns a hard coded list as the list of suggestions instead of their completions. Your job is to implement the functionality in `Suggester.java` to return a better set of suggestions, given a user input. To help you build the list of suggestions, we have provided a log file containing about 440,000 actual user searches. The log file (query.log) contains one line per user search. Here is an example line from this log file:
```
ip=24.116.26.171&iplat=33.585&iplon=-88.414&t=soft&match=Software+Engineer
```

There's also a sample log file with only 9 log lines, sample_query.log you can use for development/testing.

The format of each line is similar to URL parameters, containing a set of name/value pairs. Each name value pair looks like "name=value", and they are separated by ampersand "&". In addition, the names and values are URL encoded. The code for parsing the input file is already written (`LogReader`). There's a call back method in `Suggester.java` called `processEntries` that's called on every input line. It provides a map containing these keys and values.

So in the example line above, essentially you have a map with the following entries:

| Name  | Value          | Meaning                                 |
|-------|----------------|-----------------------------------------|
| ip    | 24.116.26.171  | The user's IP address                   |
| iplat | 33.585         | The latitude for the user's IP number   |
| iplon | -88.414        | The longitude for the user's IP number  |
| t     | soft           | What the user entered for his or her search |
| match | Software Engineer | The actual well-formatted matched job title |

You don't necessarily have to use all the fields provided.

## WHAT CODE TO WRITE

You will need to implement the methods `processEntries` and `getTopSuggestions`. You can modify the Suggester class as needed, as well as create any other helper classes/data structures.

As with many problems, there are competing interests to think about. Here are the priorities, most important first:

1. The suggestor gives suggestions that are likely to be useful to the user.
2. The suggestor is fast and has a reasonable runtime complexity, since it's called for every keystroke the user types.
3. The suggestor has reasonable runtime memory requirements. The runner script currently sets this to 512MB
4. Startup/initialization time - faster is better, but this is less important than runtime speed.

Once you're satisfied with your implementation, please write up a short English description of how you solved the problem, and what you would do to further improve your solution in a text filed named SOLUTION.md. Please note that your solution must be completely your own and you may not use any 3rd party libraries or code that has not already been provided to you.

## BUILDING AND RUNNING

To run the Suggester:

```
./autocomplete.sh query.log
```

It should prompt you to enter a prefix and display a list of suggestions. For testing you may use the smaller input file sample_query.log.

Note: autocomplete.sh requires [Gradle](https://gradle.org/) and Java 8 to work.


