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
import host.fai.lib.faiNumber.BinaryUtil;
import host.fai.lib.faiNumber.EmptyStringException;
import host.fai.lib.faiNumber.NumberUnderFlowException;
import host.fai.lib.faiNumber.NumberOverFlowException;
import java.util.Random;
/**
 * <p>The <code>BinaryUtilTest</code> is a test unit for
 * {@code host.fai.lib.faiNumber.BinaryUtil}.
 *
 * @author  Khang Hoang Nguyen
 **/
final class BinaryUtilTest{
    public static void main(String[] args){
        final String separator = String.format("****************************************%n");
        BinaryUtilTestHelper.toInt();
        System.out.println(separator);
        BinaryUtilTestHelper.toLong();
    }
}

final class BinaryUtilTestHelper{
    public static void toInt(){
        long start, end, result;
        final String failMsg = "BinaryUtil.toInt() test fail.";
        System.out.println("=== BinaryUtil.toInt(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i != 0; i++){
            if( BinaryUtil.toInt(Integer.toBinaryString(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        for (int i = 0; i != Integer.MAX_VALUE; i++){
            if( BinaryUtil.toInt(Integer.toBinaryString(i)) != i ) {
                System.out.println("Wrong :" + i);
                throw new RuntimeException(failMsg);
            }
        }
        if( BinaryUtil.toInt(Integer.toBinaryString(Integer.MAX_VALUE)) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Parse with leading 0");
        // 4294967295 unsigned or -1 signed with leading zeroes.
        if ( BinaryUtil.toInt("0000000011111111111111111111111111111111") != -1 ) throw new RuntimeException(failMsg);
        // Integer.MAX_VALUE with leading zeroes.
        if ( BinaryUtil.toInt("0000000001111111111111111111111111111111") != 2147483647 ) throw new RuntimeException(failMsg);
        // 10 with leading zeroes.
        if ( BinaryUtil.toInt("0001010") != 10 ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( BinaryUtil.toInt("000000000000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( BinaryUtil.toInt("000000000") != 0 ) throw new RuntimeException(failMsg);
        if ( BinaryUtil.toInt("00") != 0 ) throw new RuntimeException(failMsg);
        if ( BinaryUtil.toInt("0") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing invalid format");
        // 1 too many & 1 digit too many.
        try{
            BinaryUtil.toInt("100000000000000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{
            BinaryUtil.toInt("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            BinaryUtil.toInt("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            BinaryUtil.toInt("111111111112");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signed where unsigned.
        try{
            BinaryUtil.toInt("+01");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            BinaryUtil.toInt("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            BinaryUtil.toInt("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}

        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== BinaryUtil.toInt(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

    public static void toLong(){
        long start, end, result;
        final String failMsg = "BinaryUtil.toLong() test fail.";
        System.out.println("=== BinaryUtil.toLong(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing 1 billion numbers from Long.MIN_VALUE, 1 billion below zero, 1 billion above zero, and 1 billion below Long.MAX_VALUE.");
        for (long i = Long.MIN_VALUE; i != Long.MIN_VALUE + 1000000000L; i++){
            if( BinaryUtil.toLong(Long.toBinaryString(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done lowest range.");

        for (long i = Long.MAX_VALUE - 1000000000L; i != Long.MAX_VALUE; i++){
            if( BinaryUtil.toLong(Long.toBinaryString(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        if( BinaryUtil.toLong(Long.toBinaryString(Long.MAX_VALUE)) != Long.MAX_VALUE ) throw new RuntimeException(failMsg);

        System.out.println("Done higest range.");

        for (long i = 0 ; i > 0 - 1000000001L; i--){
            if( BinaryUtil.toLong(Long.toBinaryString(i)) != i ) {
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }

        System.out.println("Done 0 low range");

        for (long i = 0 ; i < 0 + 1000000001L; i++){
            if( BinaryUtil.toLong(Long.toBinaryString(i)) != i ) {
                System.out.println("Wrong " + i);
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
            if( BinaryUtil.toLong(Long.toBinaryString(number)) != number ){
                System.out.println("Wrong " + i);
                throw new RuntimeException(failMsg);
            }
        }
        System.out.println("TEST 2: Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Parsing with leading zeroes.");
        // 18446744073709551615 unsigned or -1L signed with leading zeroes.
        if( BinaryUtil.toLong("00001111111111111111111111111111111111111111111111111111111111111111") != -1L ) throw new RuntimeException(failMsg);
        // Long.MAX_VALUE with leading zeroes.
        if( BinaryUtil.toLong("00111111111111111111111111111111111111111111111111111111111111111") != 9223372036854775807L ) throw new RuntimeException(failMsg);
        // 80000000000500 with leading zeroes.
        if( BinaryUtil.toLong("00000000000000000010010001100001001110011100101010000000111110100") != 80000000000500L ) throw new RuntimeException(failMsg);
        // Zeroes.
        if( BinaryUtil.toLong("0000000") != 0L ) throw new RuntimeException(failMsg);
        if( BinaryUtil.toLong("0") != 0L ) throw new RuntimeException(failMsg);
        if( BinaryUtil.toLong("00000") != 0L ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3: Passed.");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 4: Parsing invalid format");
        // 1 too many & 1 digit too many.
        try{
            BinaryUtil.toLong("10000000000000000000000000000000000000000000000000000000000000000");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Incorrect digit.
        try{
            BinaryUtil.toLong("\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            BinaryUtil.toLong("/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            BinaryUtil.toLong("11111112");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Signed where unsigned.
        try{
            BinaryUtil.toLong("-0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            BinaryUtil.toLong("+01");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string.
        try{
            BinaryUtil.toLong("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 4: Passed.");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== BinaryUtil.toLong(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
}
