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
import host.fai.lib.faiNumber.NumberStringUtil;
import host.fai.lib.faiNumber.EmptyStringException;
import host.fai.lib.faiNumber.NumberUnderFlowException;
import host.fai.lib.faiNumber.NumberOverFlowException;
import java.util.Random;
/**
 * <p>The <codeNumberStringUtilTest</code> is a test unit for
 * {@code host.fai.lib.faiNumber.NumberStringUtil}.
 *
 * @author  Khang Hoang Nguyen
 **/
final class NumberStringUtilTest{
    public static void main(String[] args){
        final String separator = String.format("****************************************%n");
        test.isInteger();
        System.out.println(separator);
        test.isUnsignedInteger();
        System.out.println(separator);
        test.isBinary();
        System.out.println(separator);
        test.isUnsignedBinary();
        System.out.println(separator);
        test.isOctal();
        System.out.println(separator);
        test.isUnsignedOctal();
        System.out.println(separator);
        test.isHex();
        System.out.println(separator);
        test.isUnsignedHex();
        test.isBase();
        System.out.println(separator);
        test.isUnsignedBase();
        System.out.println(separator);
        test.isDigit();
        System.out.println(separator);
        test.isBinaryDigit();
        System.out.println(separator);
        test.isOctalDigit();
        System.out.println(separator);
        test.isHexDigit();
        System.out.println(separator);
        test.isBaseDigit();
        System.out.println(separator);
        test.assumeIsOdd();
        System.out.println(separator);
        test.assumeIsEven();
        System.out.println(separator);
        test.assumeCompare();
        System.out.println(separator);
        test.assumeCompareAllBase();
        System.out.println(separator);
        test.compare();
        System.out.println(separator);
        test.compareAsBase();
    }
}

final class test{
    public static void isInteger(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isInteger() test fail.";
        System.out.println("=== NumberStringUtil.isInteger(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        if ( NumberStringUtil.isInteger("00123456789") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isInteger("+9") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isInteger("-9") != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        if ( NumberStringUtil.isInteger("/") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isInteger(":") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isInteger("a") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isInteger("\0") != false ) throw new RuntimeException(failMsg);
        // Empty String & Empty Signs.
        if ( NumberStringUtil.isInteger("") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isInteger("+") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isInteger("-") != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isInteger(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isUnsignedInteger(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isUnsignedInteger() test fail.";
        System.out.println("=== NumberStringUtil.isUnsignedInteger(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        if ( NumberStringUtil.isUnsignedInteger("0123456789") != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        if ( NumberStringUtil.isUnsignedInteger("/") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedInteger(":") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedInteger("a") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedInteger("\0") != false ) throw new RuntimeException(failMsg);
        // Empty String & Signs.
        if ( NumberStringUtil.isUnsignedInteger("") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedInteger("+9") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedInteger("-9") != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isUnsignedInteger(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isBinary(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isBinary() test fail.";
        System.out.println("=== NumberStringUtil.isBinary(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        if ( NumberStringUtil.isBinary("01") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBinary("+0") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBinary("-0") != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        if ( NumberStringUtil.isBinary("/") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBinary("2") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBinary("a") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBinary("\0") != false ) throw new RuntimeException(failMsg);
        // Empty String & Empty Signs.
        if ( NumberStringUtil.isBinary("") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBinary("+") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBinary("-") != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isBinary(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isUnsignedBinary(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isUnsignedBinary() test fail.";
        System.out.println("=== NumberStringUtil.isUnsignedBinary(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        if ( NumberStringUtil.isUnsignedBinary("01") != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        if ( NumberStringUtil.isUnsignedBinary("/") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedBinary("2") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBinary("a") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBinary("\0") != false ) throw new RuntimeException(failMsg);
        // Empty String & Signs.
        if ( NumberStringUtil.isUnsignedBinary("") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBinary("+9") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBinary("-9") != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isUnsignedBinary(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isOctal(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isOctal() test fail.";
        System.out.println("=== NumberStringUtil.isOctal(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        if ( NumberStringUtil.isOctal("01234567") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isOctal("+0") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isOctal("-0") != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        // Right before and right after digit/character as digit in ASCII code, also \0.
        if ( NumberStringUtil.isOctal("/") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isOctal("8") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isOctal("a") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isOctal("\0") != false ) throw new RuntimeException(failMsg);
        // Empty String & Empty Signs.
        if ( NumberStringUtil.isOctal("") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isOctal("+") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isOctal("-") != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isOctal(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isUnsignedOctal(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isUnsignedOctal() test fail.";
        System.out.println("=== NumberStringUtil.isUnsignedOctal(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        if ( NumberStringUtil.isUnsignedOctal("01234567") != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        // Right before and right after digit/character as digit in ASCII code, also \0.
        if ( NumberStringUtil.isUnsignedOctal("/") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedOctal("8") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedOctal("a") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedOctal("\0") != false ) throw new RuntimeException(failMsg);
        // Empty String & Signs.
        if ( NumberStringUtil.isUnsignedOctal("") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedOctal("+9") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedOctal("-9") != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isUnsignedOctal(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isHex(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isHex() test fail.";
        System.out.println("=== NumberStringUtil.isHex(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        if ( NumberStringUtil.isHex("0123456789") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isHex("abcdef") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isHex("ABCDEF") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isHex("+0") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isHex("-0") != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        // Right before and right after digit/character as digit in ASCII code, also \0.
        if ( NumberStringUtil.isHex("/") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isHex(":") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isHex("`") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isHex("g") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isHex("@") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isHex("G") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isHex("\0") != false ) throw new RuntimeException(failMsg);
        // Empty String & Empty Signs.
        if ( NumberStringUtil.isHex("") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isHex("+") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isHex("-") != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isHex(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isUnsignedHex(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isUnsignedHex() test fail.";
        System.out.println("=== NumberStringUtil.isUnsignedHex(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        if ( NumberStringUtil.isUnsignedHex("0123456789") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedHex("abcdef") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedHex("ABCDEF") != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        // Right before and right after digit/character as digit in ASCII code, also \0.
        if ( NumberStringUtil.isUnsignedHex("/") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedHex(":") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedHex("@") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedHex("G") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedHex("`") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedHex("g") != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedHex("\0") != false ) throw new RuntimeException(failMsg);
        // Empty String & Signs.
        if ( NumberStringUtil.isUnsignedHex("") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedHex("+9") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedHex("-9") != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isUnsignedHex(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isBase(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isBase() test fail.";
        System.out.println("=== NumberStringUtil.isBase(String input, int base) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        // Base 2
        if ( NumberStringUtil.isBase("01", 2) != true ) throw new RuntimeException(failMsg);
        // Base 10
        if ( NumberStringUtil.isBase("0123456789", 10) != true ) throw new RuntimeException(failMsg);
        // Base 16
        if ( NumberStringUtil.isBase("0123456789", 11) != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBase("aA", 11) != true ) throw new RuntimeException(failMsg);
        // Base 36
        if ( NumberStringUtil.isBase("0123456789", 36) != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBase("abcdefghijklmnopqrstuvwxyz", 36) != true ) throw new RuntimeException(failMsg);        
        if ( NumberStringUtil.isBase("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 36) != true ) throw new RuntimeException(failMsg);        
        // Signs
        if ( NumberStringUtil.isBase("+0", 2) != true ) throw new RuntimeException(failMsg);        
        if ( NumberStringUtil.isBase("-0", 2) != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        // Right before and right after digit/character as digit in ASCII code, also \0.
        // Base 2
        if ( NumberStringUtil.isBase("/", 2) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBase("2", 2) != false ) throw new RuntimeException();
        // Base 10
        if ( NumberStringUtil.isBase("/", 10) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBase(":", 10) != false ) throw new RuntimeException(failMsg);
        // Base 11
        if ( NumberStringUtil.isBase("/", 11) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBase(":", 11) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBase("@", 11) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBase("B", 11) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBase("`", 11) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBase("b", 11) != false ) throw new RuntimeException(failMsg);
        // Base 36  
        if ( NumberStringUtil.isBase("/", 36) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBase(":", 36) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBase("@", 36) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBase("[", 36) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBase("`", 36) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBase("{", 36) != false ) throw new RuntimeException(failMsg);
        // Empty String, Empty Signs, & \0.
        if ( NumberStringUtil.isBase("", 2) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBase("+", 2) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBase("-", 2) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBase("\0", 2) != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Errors check.");
        try{
            NumberStringUtil.isBase("", 1);
            throw new RuntimeException(failMsg);
        } catch (UnsupportedOperationException e){}

        try{
            NumberStringUtil.isBase("", 37);
            throw new RuntimeException(failMsg);
        } catch (UnsupportedOperationException e){}

        try{
            NumberStringUtil.isBase(null, 35);
            throw new RuntimeException(failMsg);
        } catch (NullPointerException e){}

        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isBase(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isUnsignedBase(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isUnsignedBase() test fail.";
        System.out.println("=== NumberStringUtil.isUnsignedBase(String input, int base) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
 
 
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        // Base 2
        if ( NumberStringUtil.isUnsignedBase("01", 2) != true ) throw new RuntimeException(failMsg);
        // Base 10
        if ( NumberStringUtil.isUnsignedBase("0123456789", 10) != true ) throw new RuntimeException(failMsg);
        // Base 16
        if ( NumberStringUtil.isUnsignedBase("0123456789", 11) != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBase("aA", 11) != true ) throw new RuntimeException(failMsg);
        // Base 36
        if ( NumberStringUtil.isUnsignedBase("0123456789", 36) != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBase("abcdefghijklmnopqrstuvwxyz", 36) != true ) throw new RuntimeException(failMsg);        
        if ( NumberStringUtil.isUnsignedBase("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 36) != true ) throw new RuntimeException(failMsg);        
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
 
         System.out.println("TEST 2: Test false.");
        // Right before and right after digit/character as digit in ASCII code, also \0.
        // Base 2
        if ( NumberStringUtil.isUnsignedBase("/", 2) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedBase("2", 2) != false ) throw new RuntimeException();
        // Base 10
        if ( NumberStringUtil.isUnsignedBase("/", 10) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedBase(":", 10) != false ) throw new RuntimeException(failMsg);
        // Base 11
        if ( NumberStringUtil.isUnsignedBase("/", 11) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedBase(":", 11) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBase("@", 11) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedBase("B", 11) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBase("`", 11) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedBase("b", 11) != false ) throw new RuntimeException(failMsg);
        // Base 36  
        if ( NumberStringUtil.isUnsignedBase("/", 36) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedBase(":", 36) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBase("@", 36) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedBase("[", 36) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBase("`", 36) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isUnsignedBase("{", 36) != false ) throw new RuntimeException(failMsg);
        // Empty String, Signs & 0.
        if ( NumberStringUtil.isUnsignedBase("", 2) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBase("+0", 2) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBase("-0", 2) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isUnsignedBase("\0", 2) != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Errors check.");
        try{
            NumberStringUtil.isUnsignedBase("", 1);
            throw new RuntimeException(failMsg);
        } catch (UnsupportedOperationException e){}

        try{
            NumberStringUtil.isUnsignedBase("", 37);
            throw new RuntimeException(failMsg);
        } catch (UnsupportedOperationException e){}

        try{
            NumberStringUtil.isUnsignedBase(null, 35);
            throw new RuntimeException(failMsg);
        } catch (NullPointerException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isUnsignedBase(String input, int base) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isDigit(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isDigit() test fail.";
        System.out.println("=== NumberStringUtil.isDigit(char input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        for ( char i = '0'; i <= '9'; i++ ){
            if ( NumberStringUtil.isDigit(i) != true ) throw new RuntimeException(failMsg);
        }
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        if ( NumberStringUtil.isDigit('/') != false ) throw new RuntimeException();
        if ( NumberStringUtil.isDigit(':') != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isDigit('a') != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isDigit('\0') != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isDigit(char input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

    public static void isBinaryDigit(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isBinaryDigit() test fail.";
        System.out.println("=== NumberStringUtil.isBinaryDigit(char input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        for ( char i = '0'; i <= '1'; i++ ){
            if ( NumberStringUtil.isBinaryDigit(i) != true ) throw new RuntimeException(failMsg);
        }
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        if ( NumberStringUtil.isBinaryDigit('/') != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBinaryDigit('2') != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBinaryDigit('a') != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBinaryDigit('\0') != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isBinaryDigit(char input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

    public static void isOctalDigit(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isOctalDigit() test fail.";
        System.out.println("=== NumberStringUtil.isOctalDigit(char input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        for ( char i = '0'; i <= '7'; i++ ){
            if ( NumberStringUtil.isOctalDigit(i) != true ) throw new RuntimeException(failMsg);
        }
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        if ( NumberStringUtil.isOctalDigit('/') != false ) throw new RuntimeException();
        if ( NumberStringUtil.isOctalDigit('8') != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isOctalDigit('a') != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isOctalDigit('\0') != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isOctalDigit(char input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isHexDigit(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isHexDigit() test fail.";
        System.out.println("=== NumberStringUtil.isHexDigit(char input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        for ( char i = '0'; i <= '9'; i++ ){
            if ( NumberStringUtil.isHexDigit(i) != true ) throw new RuntimeException(failMsg);
        }
        for ( char i = 'a'; i <= 'f'; i++ ){
            if ( NumberStringUtil.isHexDigit(i) != true ) throw new RuntimeException(failMsg);
        }
        for ( char i = 'A'; i <= 'F'; i++ ){
            if ( NumberStringUtil.isHexDigit(i) != true ) throw new RuntimeException(failMsg);
        }
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        if ( NumberStringUtil.isHexDigit('/') != false ) throw new RuntimeException();
        if ( NumberStringUtil.isHexDigit(':') != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isHexDigit('`') != false ) throw new RuntimeException();
        if ( NumberStringUtil.isHexDigit('g') != false ) throw new RuntimeException();
        if ( NumberStringUtil.isHexDigit('@') != false ) throw new RuntimeException();
        if ( NumberStringUtil.isHexDigit('G') != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isHexDigit('\0') != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isHexDigit(char input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void isBaseDigit(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.isBaseDigit() test fail.";
        System.out.println("=== NumberStringUtil.isBaseDigit(char input, int base) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        // base 2
        for ( char i = '0'; i <= '1'; i++ ){
            if ( NumberStringUtil.isBaseDigit(i, 2) != true ) throw new RuntimeException(failMsg);
        }
        // base 10
        for ( char i = '0'; i <= '9'; i++ ){
            if ( NumberStringUtil.isBaseDigit(i, 10) != true ) throw new RuntimeException(failMsg);
        }
        // base 11
        for ( char i = '0'; i <= '9'; i++ ){
            if ( NumberStringUtil.isBaseDigit(i, 11) != true ) throw new RuntimeException(failMsg);
        }
        if ( NumberStringUtil.isBaseDigit('a', 11) != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBaseDigit('A', 11) != true ) throw new RuntimeException(failMsg);
        // base 36
        for ( char i = '0'; i <= '9'; i++ ){
            if ( NumberStringUtil.isBaseDigit(i, 36) != true ) throw new RuntimeException(failMsg);
        }
        for ( char i = 'a'; i <= 'z'; i++ ){
            if ( NumberStringUtil.isBaseDigit(i, 36) != true ) throw new RuntimeException(failMsg);
        }
        for ( char i = 'A'; i <= 'Z'; i++ ){
            if ( NumberStringUtil.isBaseDigit(i, 36) != true ) throw new RuntimeException(failMsg);
        }        
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 2: Test false.");
        // base 2
        if ( NumberStringUtil.isBaseDigit('/', 2) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBaseDigit('2', 2) != false ) throw new RuntimeException(failMsg);
        // base 10
        if ( NumberStringUtil.isBaseDigit('/', 10) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBaseDigit('a', 10) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBaseDigit('A', 10) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBaseDigit('\0', 10) != false ) throw new RuntimeException(failMsg);
        // base 11
        if ( NumberStringUtil.isBaseDigit('/', 11) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBaseDigit('b', 11) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBaseDigit('B', 11) != false ) throw new RuntimeException(failMsg);
        // base 36
        if ( NumberStringUtil.isBaseDigit('/', 36) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBaseDigit(':', 36) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBaseDigit('`', 36) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBaseDigit('{', 36) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBaseDigit('@', 36) != false ) throw new RuntimeException();
        if ( NumberStringUtil.isBaseDigit('[', 36) != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.isBaseDigit('\0', 36) != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Errors check.");
        try{
            NumberStringUtil.isBaseDigit('\0', 1);
            throw new RuntimeException(failMsg);
        } catch (UnsupportedOperationException e){}

        try{
            NumberStringUtil.isBaseDigit('\0', 37);
            throw new RuntimeException(failMsg);
        } catch (UnsupportedOperationException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.isBaseDigit(char input, int base) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

    public static void assumeIsOdd(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.assumeIsOdd() test fail.";
        System.out.println("=== NumberStringUtil.assumeIsOdd(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
    
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        if ( NumberStringUtil.assumeIsOdd("21") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeIsOdd("1") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeIsOdd("555555") != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 2: Test false.");
        if ( NumberStringUtil.assumeIsOdd("12") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeIsOdd("0") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeIsOdd("8888888") != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Errors check.");
        try{
            NumberStringUtil.assumeIsOdd("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}

        try{
            NumberStringUtil.assumeIsOdd(null);
            throw new RuntimeException(failMsg);
        } catch (NullPointerException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.assumeIsOdd(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

    public static void assumeIsEven(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.assumeIsEven() test fail.";
        System.out.println("=== NumberStringUtil.assumeIsEven(String input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
    
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test true.");
        if ( NumberStringUtil.assumeIsEven("22") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeIsEven("0") != true ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeIsEven("88888888") != true ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 2: Test false.");
        if ( NumberStringUtil.assumeIsEven("21") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeIsEven("1") != false ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeIsEven("5555555555") != false ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 3: Errors check.");
        try{
            NumberStringUtil.assumeIsEven("");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}

        try{
            NumberStringUtil.assumeIsEven(null);
            throw new RuntimeException(failMsg);
        } catch (NullPointerException e){}
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.assumeIsEven(String input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

    public static void assumeCompare(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.assumeCompare() test fail.";
        System.out.println("=== NumberStringUtil.assumeCompare(String firstString, String secondString) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
    
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test Smaller Than.");
        // Empty Compare.
        if ( NumberStringUtil.assumeCompare("", "+") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("", "0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("", "21") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("", "-1") != -1 ) throw new RuntimeException(failMsg);
        // Neg To.
        if ( NumberStringUtil.assumeCompare("-1", "0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-1", "-0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-1", "+0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-1", "-") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-1", "+") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-9", "-1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-1585", "0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-1585", "1585") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-58585", "-58584") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-58585", "-35") != -1 ) throw new RuntimeException(failMsg);
        // Pos To.
        if ( NumberStringUtil.assumeCompare("1", "9") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("250", "2499") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("250", "251") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("2785", "+2787") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("+27", "+2574") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("+851", "852") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("358585", "358587") != -1 ) throw new RuntimeException(failMsg);
        // Zeroes 
        if ( NumberStringUtil.assumeCompare("+", "1") != -1 ) throw new RuntimeException(failMsg);    
        if ( NumberStringUtil.assumeCompare("-", "1") != -1 ) throw new RuntimeException(failMsg);    
        if ( NumberStringUtil.assumeCompare("-0", "1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("+0", "1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("0", "1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("0", "258") != -1 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 2: Test Equal.");
        // Empty & Just Sign.
        if ( NumberStringUtil.assumeCompare("", "") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-", "+") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("+", "-") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("+", "+") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-", "-") != 0 ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( NumberStringUtil.assumeCompare("-0", "-0") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-0", "+0") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("0", "+0") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("0", "0") != 0 ) throw new RuntimeException(failMsg);
        // Leading Zeroes.
        if ( NumberStringUtil.assumeCompare("-000250", "-0250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-0000", "+00000") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("0250", "+000250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("250", "+0000250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-250", "-0000250") != 0 ) throw new RuntimeException(failMsg);
        // 1s.
        if ( NumberStringUtil.assumeCompare("+1", "1") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("+1", "+1") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-1", "-1") != 0 ) throw new RuntimeException(failMsg);
        // Other.
        if ( NumberStringUtil.assumeCompare("+250", "250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("+85007", "+85007") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-9875778787", "-9875778787") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 3: Test Larger than.");
        // Empty Compare.
        if ( NumberStringUtil.assumeCompare("+", "") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("0", "") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("21", "") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-1", "") != 1 ) throw new RuntimeException(failMsg);
        // Pos To.
        if ( NumberStringUtil.assumeCompare("1", "0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("1", "-0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("1", "+0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("1", "-") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("1", "+") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("9", "1") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("1585", "0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("1585", "-1585") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("58585", "58584") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("58585", "35") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("4789", "+4788") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("+4789", "4788") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("+4789", "+4788") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("4789", "4788") != 1 ) throw new RuntimeException(failMsg);
        // Neg To.
        if ( NumberStringUtil.assumeCompare("-1", "-9") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-250", "-2499") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-250", "-251") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-2785", "-2787") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-27", "-2574") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-851", "-852") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("-358585", "-358587") != 1 ) throw new RuntimeException(failMsg);
        // Zeroes
        if ( NumberStringUtil.assumeCompare("1", "+") != 1 ) throw new RuntimeException(failMsg);    
        if ( NumberStringUtil.assumeCompare("1", "-") != 1 ) throw new RuntimeException(failMsg);    
        if ( NumberStringUtil.assumeCompare("1", "-0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("1", "+0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("1", "0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompare("258", "0") != 1 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.assumeCompare(String firstString, String secondString) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void assumeCompareAllBase(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.assumeCompareAllBase() test fail.";
        System.out.println("=== NumberStringUtil.assumeCompareAllBase(String firstString, String secondString) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
    
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test Smaller Than.");
        // Empty Compare.
        if ( NumberStringUtil.assumeCompareAllBase("", "+") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("", "0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("", "21") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("", "-1") != -1 ) throw new RuntimeException(failMsg);
        // Neg To.
        if ( NumberStringUtil.assumeCompareAllBase("-1", "0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-1", "-0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-1", "+0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-1", "-") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-1", "+") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-9", "-1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-1585", "0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-1585", "1585") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-58585", "-58584") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-58585", "-35") != -1 ) throw new RuntimeException(failMsg);
        // Pos To.
        if ( NumberStringUtil.assumeCompareAllBase("1", "9") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("250", "2499") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("250", "251") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("2785", "+2787") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("+27", "+2574") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("+851", "852") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("358585", "358587") != -1 ) throw new RuntimeException(failMsg);
        // Zeroes 
        if ( NumberStringUtil.assumeCompareAllBase("+", "1") != -1 ) throw new RuntimeException(failMsg);    
        if ( NumberStringUtil.assumeCompareAllBase("-", "1") != -1 ) throw new RuntimeException(failMsg);    
        if ( NumberStringUtil.assumeCompareAllBase("-0", "1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("+0", "1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("0", "1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("0", "258") != -1 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 2: Test Equal.");
        // Empty & Just Sign.
        if ( NumberStringUtil.assumeCompareAllBase("", "") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-", "+") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("+", "-") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("+", "+") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-", "-") != 0 ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( NumberStringUtil.assumeCompareAllBase("-0", "-0") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-0", "+0") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("0", "+0") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("0", "0") != 0 ) throw new RuntimeException(failMsg);
        // Leading Zeroes.
        if ( NumberStringUtil.assumeCompareAllBase("-000250", "-0250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-0000", "+00000") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("0250", "+000250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("250", "+0000250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-250", "-0000250") != 0 ) throw new RuntimeException(failMsg);
        // 1s.
        if ( NumberStringUtil.assumeCompareAllBase("+1", "1") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("+1", "+1") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-1", "-1") != 0 ) throw new RuntimeException(failMsg);
        // Other.
        if ( NumberStringUtil.assumeCompareAllBase("+250", "250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("+85007", "+85007") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-9875778787", "-9875778787") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 3: Test Larger than.");
        // Empty Compare.
        if ( NumberStringUtil.assumeCompareAllBase("+", "") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("0", "") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("21", "") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-1", "") != 1 ) throw new RuntimeException(failMsg);
        // Pos To.
        if ( NumberStringUtil.assumeCompareAllBase("1", "0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("1", "-0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("1", "+0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("1", "-") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("1", "+") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("9", "1") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("1585", "0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("1585", "-1585") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("58585", "58584") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("58585", "35") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("4789", "+4788") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("+4789", "4788") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("+4789", "+4788") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("4789", "4788") != 1 ) throw new RuntimeException(failMsg);
        // Neg To.
        if ( NumberStringUtil.assumeCompareAllBase("-1", "-9") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-250", "-2499") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-250", "-251") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-2785", "-2787") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-27", "-2574") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-851", "-852") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-358585", "-358587") != 1 ) throw new RuntimeException(failMsg);
        // Zeroes
        if ( NumberStringUtil.assumeCompareAllBase("1", "+") != 1 ) throw new RuntimeException(failMsg);    
        if ( NumberStringUtil.assumeCompareAllBase("1", "-") != 1 ) throw new RuntimeException(failMsg);    
        if ( NumberStringUtil.assumeCompareAllBase("1", "-0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("1", "+0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("1", "0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("258", "0") != 1 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 4: Other bases.");
        // Base11
        if ( NumberStringUtil.assumeCompareAllBase("a0a", "a10") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-a10", "-a0a") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("a", "A") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("a0a", "a09") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-aa9", "-aaa") != 1 ) throw new RuntimeException(failMsg);       
        // Base 36.
        if ( NumberStringUtil.assumeCompareAllBase("zj", "zk") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-zb", "-za") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("zva", "zv0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.assumeCompareAllBase("-zzy", "-zzz") != 1 ) throw new RuntimeException(failMsg);       
        System.out.println("TEST 4 Passed");
        System.out.println();System.out.println();System.out.println();
        
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.assumeCompareAllBase(String firstString, String secondString) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void compare(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.compare() test fail.";
        System.out.println("=== NumberStringUtil.compare(String firstString, String secondString) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
    
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test Smaller Than.");
        // Neg To.
        if ( NumberStringUtil.compare("-1", "0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-1", "-0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-1", "+0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-9", "-1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-1585", "0") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-1585", "1585") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-58585", "-58584") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-58585", "-35") != -1 ) throw new RuntimeException(failMsg);
        // Pos To.
        if ( NumberStringUtil.compare("1", "9") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("250", "2499") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("250", "251") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("2785", "+2787") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("+27", "+2574") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("+851", "852") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("358585", "358587") != -1 ) throw new RuntimeException(failMsg);
        // Zeroes 
        if ( NumberStringUtil.compare("-0", "1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("+0", "1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("0", "1") != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("0", "258") != -1 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 2: Test Equal.");
        // Zeroes.
        if ( NumberStringUtil.compare("-0000", "-0") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-0", "+000000") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("0", "+0") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("0", "0") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("+0", "-0") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("+0", "0") != 0 ) throw new RuntimeException(failMsg);

        // Leading Zeroes.
        if ( NumberStringUtil.compare("-000250", "-0250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-0000", "+00000") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("0250", "+000250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("250", "+0000250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-250", "-0000250") != 0 ) throw new RuntimeException(failMsg);
        // 1s.
        if ( NumberStringUtil.compare("+1", "1") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("+1", "+1") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-1", "-1") != 0 ) throw new RuntimeException(failMsg);
        // Other.
        if ( NumberStringUtil.compare("+250", "250") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("+85007", "+85007") != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-9875778787", "-9875778787") != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 3: Test Larger than.");
        // Pos To.
        if ( NumberStringUtil.compare("1", "0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("1", "-0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("1", "+0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("9", "1") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("1585", "0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("1585", "-1585") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("58585", "58584") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("58585", "35") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("4789", "+4788") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("+4789", "4788") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("+4789", "+4788") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("4789", "4788") != 1 ) throw new RuntimeException(failMsg);
        // Neg To.
        if ( NumberStringUtil.compare("-1", "-9") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-250", "-2499") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-250", "-251") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-2785", "-2787") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-27", "-2574") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-851", "-852") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("-358585", "-358587") != 1 ) throw new RuntimeException(failMsg);
        // Zeroes
        if ( NumberStringUtil.compare("001", "-0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("1", "+0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("001", "0") != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compare("258", "0") != 1 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();


        System.out.println("TEST 4: Errors.");
        // Single signs.
        try{
            NumberStringUtil.compare("+", "123");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("-", "123");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("123", "-");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("123", "+");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}


        // length 1 incorrect digit.
        try{
            NumberStringUtil.compare("\0", "1234");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compare("/", "1234");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare(":", "1234");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("a", "1234");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("1234", "\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compare("1234", "/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("1234", ":");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("1234", "a");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // equal length incorrect digit.
        try{
            NumberStringUtil.compare("12345678\0", "123456789");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compare("12345678/", "123456789");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("12345678:", "123456789");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("12345678a", "123456789");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("123456789", "12345678\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compare("123456789", "12345678/");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("123456789", "12345678:");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("123456789", "12345678a");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // different length incorrect digit.
        try{
            NumberStringUtil.compare("12345678\0", "12345678901");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compare("12345678/", "12345678901");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("12345678:", "12345");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("12345678a", "12345");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("12345678901", "12345678\0");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compare("12345678901", "12345678/" );
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("12345", "12345678:");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compare("12345", "12345678a");
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Empty string
        try{
            NumberStringUtil.compare("", "123");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}

        try{
            NumberStringUtil.compare("123", "");
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 4 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.compare(String firstString, String secondString) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }
    
    public static void compareAsBase(){
        long start, end, result;
        final String failMsg = "NumberStringUtil.compareAsBase() test fail.";
        System.out.println("=== NumberStringUtil.compareAsBase(String firstString, String secondString, int base) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
    
        start = System.currentTimeMillis();
        System.out.println("TEST 1: Test Smaller Than.");
        // Neg To.
        if ( NumberStringUtil.compareAsBase("-1", "0", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-1", "-0", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-1", "+0", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-9", "-1", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-1585", "0", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-1585", "1585", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-58585", "-58584", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-58585", "-35", 10) != -1 ) throw new RuntimeException(failMsg);
        // Pos To.
        if ( NumberStringUtil.compareAsBase("1", "9", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("250", "2499", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("250", "251", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("2785", "+2787", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("+27", "+2574", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("+851", "852", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("358585", "358587", 10) != -1 ) throw new RuntimeException(failMsg);
        // Zeroes 
        if ( NumberStringUtil.compareAsBase("-0", "1", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("+0", "1", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("0", "1", 10) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("0", "258", 10) != -1 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 2: Test Equal.");
        // Alphanumerical equality.
        if ( NumberStringUtil.compareAsBase("-abcdefghijklmnopqrstuvwxyz", "-ABCDEFGHIJKLMNOPQRSTUVWXYZ", 36) != 0 ) throw new RuntimeException(failMsg);
        // Zeroes.
        if ( NumberStringUtil.compareAsBase("-0000", "-0", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-0", "+000000", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("0", "+0", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("0", "0", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("+0", "-0", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("+0", "0", 10) != 0 ) throw new RuntimeException(failMsg);
        // Leading Zeroes.
        if ( NumberStringUtil.compareAsBase("-000250", "-0250", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-0000", "+00000", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("0250", "+000250", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("250", "+0000250", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-250", "-0000250", 10) != 0 ) throw new RuntimeException(failMsg);
        // 1s.
        if ( NumberStringUtil.compareAsBase("+1", "1", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("+1", "+1", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-1", "-1", 10) != 0 ) throw new RuntimeException(failMsg);
        // Other.
        if ( NumberStringUtil.compareAsBase("+250", "250", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("+85007", "+85007", 10) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-9875778787", "-9875778787", 10) != 0 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 2 Passed");
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("TEST 3: Test Larger than.");
        // Pos To.
        if ( NumberStringUtil.compareAsBase("1", "0", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("1", "-0", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("1", "+0", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("9", "1", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("1585", "0", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("1585", "-1585", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("58585", "58584", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("58585", "35", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("4789", "+4788", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("+4789", "4788", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("+4789", "+4788", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("4789", "4788", 10) != 1 ) throw new RuntimeException(failMsg);
        // Neg To.
        if ( NumberStringUtil.compareAsBase("-1", "-9", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-250", "-2499", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-250", "-251", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-2785", "-2787", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-27", "-2574", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-851", "-852", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-358585", "-358587", 10) != 1 ) throw new RuntimeException(failMsg);
        // Zeroes
        if ( NumberStringUtil.compareAsBase("001", "-0", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("1", "+0", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("001", "0", 10) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("258", "0", 10) != 1 ) throw new RuntimeException(failMsg);
        System.out.println("TEST 3 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 4: Other bases.");
        // Base11
        if ( NumberStringUtil.compareAsBase("a0a", "a10", 11) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-a10", "-a0a", 11) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("a", "A", 11) != 0 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("a0a", "a09", 11) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-aa9", "-aaa", 11) != 1 ) throw new RuntimeException(failMsg);       
 
        // Base 36.
        if ( NumberStringUtil.compareAsBase("z9", "za", 36) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-zb", "-za", 36) != -1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 36) != 0) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("zva", "zv0", 36) != 1 ) throw new RuntimeException(failMsg);
        if ( NumberStringUtil.compareAsBase("-zzy", "-zzz", 36) != 1 ) throw new RuntimeException(failMsg);       
        System.out.println("TEST 4 Passed");
        System.out.println();System.out.println();System.out.println();

        System.out.println("TEST 5: Errors.");
        // Single signs.
        try{
            NumberStringUtil.compareAsBase("+", "123", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("-", "123", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("123", "-", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("123", "+", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // length 1 incorrect digit.
        try{
            NumberStringUtil.compareAsBase("\0", "1234", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compareAsBase("/", "1234", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase(":", "1234", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("a", "1234", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1234", "\0", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compareAsBase("1234", "/", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1234", ":", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1234", "a", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // equal length incorrect digit.
        try{
            NumberStringUtil.compareAsBase("12345678\0", "123456789", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compareAsBase("12345678/", "123456789", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("12345678:", "123456789", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("12345678a", "123456789", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("123456789", "12345678\0", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compareAsBase("123456789", "12345678/", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("123456789", "12345678:", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("123456789", "12345678a", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // different length incorrect digit.
        try{
            NumberStringUtil.compareAsBase("12345678\0", "12345678901", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compareAsBase("12345678/", "12345678901", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("12345678:", "12345", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("12345678a", "12345", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("12345678901", "12345678\0", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}
        
        try{
            NumberStringUtil.compareAsBase("12345678901", "12345678/", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("12345", "12345678:", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("12345", "12345678a", 10);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Base 11 incorrect digit.
        try{
            NumberStringUtil.compareAsBase("/", "1", 11);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("b", "1", 11);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1", "/", 11);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1", "b", 11);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Base 36 incorrect digit.
        try{
            NumberStringUtil.compareAsBase("/", "1", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase(":", "1", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("@", "1", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("[", "1", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1", "`", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("{", "1", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1", "/", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1", ":", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1", "@", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1", "[", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1", "`", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        try{
            NumberStringUtil.compareAsBase("1", "{", 36);
            throw new RuntimeException(failMsg);
        } catch (NumberFormatException e){}

        // Unsupported Base
        try{
            NumberStringUtil.compareAsBase("000", "000", 1);
            throw new RuntimeException(failMsg);
        } catch (UnsupportedOperationException e){}

        try{
            NumberStringUtil.compareAsBase("zzz", "zzz", 37);
            throw new RuntimeException(failMsg);
        } catch (UnsupportedOperationException e){}

        // Empty string
        try{
            NumberStringUtil.compareAsBase("", "123", 10);
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}

        try{
            NumberStringUtil.compareAsBase("123", "", 10);
            throw new RuntimeException(failMsg);
        } catch (EmptyStringException e){}
        System.out.println("TEST 5 Passed");
        System.out.println();System.out.println();System.out.println();

        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== NumberStringUtil.compareAsBase(String firstString, String secondString, int base) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println(); System.out.println();
    }

}
