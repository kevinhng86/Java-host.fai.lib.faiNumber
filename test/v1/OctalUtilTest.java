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
import host.fai.lib.faiNumber.OctalUtil;
import host.fai.lib.faiNumber.EmptyStringException;
import host.fai.lib.faiNumber.NumberUnderFlowException;
import host.fai.lib.faiNumber.NumberOverFlowException;
import java.util.Random;
/**
 * <p>The <code>OctalUtilTest</code> is a test unit for
 * {@code host.fai.lib.faiNumber.OctalUtil}.
 *
 * @author  Khang Hoang Nguyen
 **/
final class OctalUtilTest{
    public static void main(String[] args){
        final String separator = String.format("****************************************%n");
        OctalUtilTestHelper.toInt();
        System.out.println(separator);
        OctalUtilTestHelper.toLong();
    }
}

final class OctalUtilTestHelper{
    public static void toInt(){
        long start, end, result;
        final String failMsg = "OctalUtil.toInt() test fail.";
        System.out.println("=== OctalUtil.toInt(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i != 0; i++){
            if( OctalUtil.toInt(Integer.toOctalString(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        for (int i = 0; i != Integer.MAX_VALUE; i++){
            if( OctalUtil.toInt(Integer.toOctalString(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        if( OctalUtil.toInt(Integer.toOctalString(Integer.MAX_VALUE)) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // 4294967295 unsigned or -1 signed with leading 0;
        if ( OctalUtil.toInt("0000000037777777777") != -1 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if ( OctalUtil.toInt("00000000017777777777") != 2147483647 ) throw new RuntimeException(failMsg);
        // 504 with leading zeroes.
        if ( OctalUtil.toInt("000770") != 504 ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( OctalUtil.toInt("000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( OctalUtil.toInt("000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( OctalUtil.toInt("00") != 0 ) throw new RuntimeException(failMsg);
        if ( OctalUtil.toInt("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many.
        try{
            OctalUtil.toInt("40000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{
            OctalUtil.toInt("377777777771");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{
            OctalUtil.toInt("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            OctalUtil.toInt("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            OctalUtil.toInt("377778");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signed where unsigned.
        try{
            OctalUtil.toInt("+01");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            OctalUtil.toInt("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            OctalUtil.toInt("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== OctalUtil.toInt(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

        public static void toLong(){
        long start, end, result;
        final String failMsg = "OctalUtil.toLong() test fail.";
        System.out.println("=== OctalUtil.toLong(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 1 billion numbers from Long.MIN_VALUE, 1 billion below zero, 1 billion above zero, and 1 billion below Long.MAX_VALUE.");
        for (long i = Long.MIN_VALUE; i != Long.MIN_VALUE + 1000000000L; i++){
            if( OctalUtil.toLong(Long.toOctalString(i)) != i ){
                System.out.println("Wrong" + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done lowest range.");

        for (long i = Long.MAX_VALUE - 1000000000L; i != Long.MAX_VALUE; i++){
            if( OctalUtil.toLong(Long.toOctalString(i)) != i ){
                System.out.println("Wrong" + i);
                throw new RuntimeException(failMsg);
            }
        }

        if( OctalUtil.toLong(Long.toOctalString(Long.MAX_VALUE)) != Long.MAX_VALUE ) throw new RuntimeException(failMsg);

        System.out.println("Done higest range.");

        for (long i = 0 ; i > 0 - 1000000000L; i--){
            if( OctalUtil.toLong(Long.toOctalString(i)) != i ){
                System.out.println("Wrong" + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done 0 low range");

        for (long i = 0 ; i < 0 + 1000000000L; i++){
            if( OctalUtil.toLong(Long.toOctalString(i)) != i ){
                System.out.println("Wrong" + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("Done 0 high range");
        System.out.println("TEST 1: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Random parsing 100 million numbers.");
        Random gen = new Random();
        long number;
        for (int i = 0; i < 100000000; i++){
            number = gen.nextLong();
            if( OctalUtil.toLong(Long.toOctalString(number)) != number ){
                System.out.println("Wrong " + number);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("TEST 2: Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing with leading zeroes.");
        // 18446744073709551615 unsigned or -1L signed with leading zeroes.
        if( OctalUtil.toLong("00001777777777777777777777") != -1L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if( OctalUtil.toLong("00777777777777777777777") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // 800000000000L with leading zeroes.
        if( OctalUtil.toLong("00000000000000000013510355640000") != 800000000000L ) throw new RuntimeException(failMsg);
        // Zeroes.
        if( OctalUtil.toLong("0000000") != 0L ) throw new RuntimeException(failMsg);
        if( OctalUtil.toLong("0") != 0L ) throw new RuntimeException(failMsg);
        if( OctalUtil.toLong("00000") != 0L ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 4: Parsing invalid format");
        // 1 too many.
        try{
            OctalUtil.toLong("2000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // 1 digit too many.
        try{
            OctalUtil.toLong("17777777777777777777771");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}


        // Incorrect digit.
        try{
            OctalUtil.toLong("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            OctalUtil.toLong("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            OctalUtil.toLong("777777778");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signed where unsigned.
        try{
            OctalUtil.toLong("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            OctalUtil.toLong("+01");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            OctalUtil.toLong("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 4: Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== OctalUtil.toLong(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
}
