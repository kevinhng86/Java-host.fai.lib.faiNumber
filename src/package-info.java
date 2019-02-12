/*
 * Copyright 2019 Khang Hoang Nguyen
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files 
 * (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 **/
/**
  * <p>host.fai.lib.faiNumber is a library that mainly deals with
  * converting strings of number to a supported primitive data type,
  * numerically validating strings or chars, numerically comparing
  * strings, and converting a supported primitive data type to numerical
  * strings.
  *
  * <p>Currently, host.fai.lib.faiNumber supports converting numerical
  * strings of radix 2, 3, 8, 10, 16 to long and int integer. This
  * library supports comparing strings of any length(bound by memory
  * limitation) of integer values of any radix between 2 to 36 without
  * needing to convert them to a primitive data type.
  *
  * <p>host.fai.lib.faiNumber's goal is to work with each number radix
  * individually so that the conversion can be perform in the most
  * optimized manner so that maximum speed and efficiency can be achived.
  * Another goal of this libary is to be able to provide support for all
  * numbering systems between 2 and 36. Future support for float,
  * double, short, and byte is also another goal of this library.
  *
  * <p>Although, this library aimed for speed, not all methods provides
  * by this library did achive maximum optimization. This library is
  * very fast and should be among the fastest for parsing numerical
  * strings of a supported base radix to integer long or integer int
  * data type. For parsing a numerical string to an int or a long
  * integer, this library is faster than the built in library as of
  * JDK 11.
  *
  * <p>This library numerical strings comparision should also be among
  * the fastest as this library utilizes a lazy but fast algorithms to
  * compare strings of numerical value. However, when it comes to
  * converting a value of integer long or integer int to strings, this
  * library is not as fast as the built in library. Thus, it is
  * recommended to use the built in library Integer.toString() or
  * Long.toString() method for converting a primitive type back to 
  * string unless a method designed specifically by this library is
  * needed.
  *
  * <p>This library does not have any dependency and will maintain that
  * way as that is also one of this library goal. 
  *
  * <p>This library does not remove any API that was released in any
  * final build versions without a notice spanning 3 major release
  * versions.
  *
  * <p>Before using some of the methods of this library, it is
  * recommended to read the documentation for what they do before using
  * them as some of the methods of this library were built for expert
  * usage. The previous is especially true for any assume**** methods
  * that provides by this library.</p>
  * 
  * <p>Check out the source code and builds of this library on
  * <a href="//github.com/kevinhng86/Java-host.fai.lib.faiNumber/">
  * Github</a>. If you like this library and would like to contribute 
  * to making this library a better library, you are more than welcome
  * to on <a href="//github.com/kevinhng86/Java-host.fai.lib.faiNumber/">
  * Github</a>.
  * 
  * @author  Khang Hoang Nguyen
  * 
  * @version  1.0.0.f
  **/ 
package host.fai.lib.faiNumber;
