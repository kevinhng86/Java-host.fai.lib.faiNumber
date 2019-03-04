# host.fai.lib.faiNumber

host.fai.lib.faiNumber is a numerical library for Java.

host.fai.lib.faiNumber mainly deals with converting strings of number to
a supported primitive data type, numerically validating strings or chars,
numerically comparing strings, and converting a supported primitive data
type to numerical strings.

Currently, host.fai.lib.faiNumber supports converting numerical strings
of radix 2, 3, 8, 10, 16 to long and int integer. This library supports
comparing strings of any length(bound by memory limitation) of integer
values of any radix between 2 to 36 without needing to convert them to
a primitive data type.

host.fai.lib.faiNumber's goal is to work with each number radix
individually so that the conversion can be performed in the most optimized
manner so that maximum speed and efficiency can be achieved. Another goal
of this library is to be able to provide support for all numbering
systems between 2 and 36. Future support for float, double, short, and byte
is also another goal of this library.

Although, this library aimed for speed, not all methods provided by this
library did achieve maximum optimization. This library is very fast and
should be among the fastest for parsing numerical strings of a supported
base radix to integer long or integer int data type. For parsing a
numerical string to an int or a long integer, this library is faster than
the built-in library as of JDK 11.

This library numerical strings comparison should also be among the
fastest as this library utilizes a lazy but fast algorithm to compare
strings of numerical value. However, when it comes to converting a value
of integer long or integer int to strings, this library is not as fast
as the built-in library. Thus, it is recommended to use the built-in
library Integer.toString() or Long.toString() method for converting a
primitive type back to string unless a method designed specifically by
this library is needed.

This library does not have any dependency and will maintain that way as
that is also one of this library goal.

This library does not remove any API that was released in any final
build versions without a notice spanning 3 major release versions.

Before using some of the methods of this library, it is recommended to
read the documentation for what they do before using them as some of the
methods of this library were built for expert usage. The previous is
especially true for any assume**** methods that provide by this library.

## Benchmark
<a href="https://github.com/kevinhng86/Java-host.fai.lib.faiNumber/blob/v1/benchmark.md">Benchmark</a>

## Help
<a href="//lib.fai.host/java/faiNumber/v1/">faiNumber's API Documentation</a>

## License
<a href="https://github.com/kevinhng86/Java-host.fai.lib.faiNumber/blob/master/LICENSE">MIT</a>
