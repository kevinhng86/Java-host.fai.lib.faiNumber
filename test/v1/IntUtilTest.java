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
import host.fai.lib.faiNumber.IntUtil;
import host.fai.lib.faiNumber.EmptyStringException;
import host.fai.lib.faiNumber.NumberUnderFlowException;
import host.fai.lib.faiNumber.NumberOverFlowException;

/**
 * <p>The <code>IntUtilTest</code> is a test unit for 
 * {@code host.fai.lib.faiNumber.IntUtil}
 * 
 * @author  Khang Hoang Nguyen
 **/
final class IntUtilTest{
    public static void main(String[] args){
        final String separator = String.format("****************************************%n");
        IntUtilTestHelper.toSignedBinary();
        System.out.println(separator);
        IntUtilTestHelper.toSignedOctal();
        System.out.println(separator);
        IntUtilTestHelper.toSignedHex();
        System.out.println(separator);
        IntUtilTestHelper.toStringAsUnsigned();
        System.out.println(separator);
        IntUtilTestHelper.toLongAsUnsigned();
        System.out.println(separator);
        IntUtilTestHelper.isOdd();
        System.out.println(separator);
        IntUtilTestHelper.isEven();
    }
}

final class IntUtilTestHelper{    
    public static void toSignedBinary(){
        long start, end, result;
        final String failMsg = "IntUtil.toSignedBinary() test fail.";
        System.out.println("=== IntUtil.toSignedBinary(int input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();
        
        start = System.currentTimeMillis();    
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for ( int i = Integer.MIN_VALUE; i < 0; i++ ){
            if ( Integer.parseInt(IntUtil.toSignedBinary(i),2) != i ) throw new RuntimeException(failMsg);            
        }
        for ( int i = 0; i < Integer.MAX_VALUE; i++ ){
            if ( Integer.parseInt(IntUtil.toSignedBinary(i),2) != i ) throw new RuntimeException(failMsg);            
        }
        if ( Integer.parseInt(IntUtil.toSignedBinary(Integer.MAX_VALUE),2) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);            
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
            
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== IntUtil.toSignedBinary(int input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }
    
    public static void toSignedOctal(){
        long start, end , result;            
        final String failMsg = "IntUtil.toSignedOctal() test fail.";
        System.out.println("=== IntUtil.toSignedOctal(int input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for ( int i = Integer.MIN_VALUE; i < 0; i++ ){
            if ( Integer.parseInt(IntUtil.toSignedOctal(i),8) != i ) throw new RuntimeException(failMsg);            
        }
        for ( int i = 0; i < Integer.MAX_VALUE; i++ ){
            if ( Integer.parseInt(IntUtil.toSignedOctal(i),8) != i ) throw new RuntimeException(failMsg);            
        }
        if ( Integer.parseInt(IntUtil.toSignedOctal(Integer.MAX_VALUE),8) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);            
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
       
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== IntUtil.toSignedOctal(int input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }
    
    public static void toSignedHex(){
        long start, end, result;
        final String failMsg = "IntUtil.toSignedHex() test fail.";
        System.out.println("=== IntUtil.toSignedHex(int input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
        for ( int i = Integer.MIN_VALUE; i < 0; i++ ){
            if ( Integer.parseInt(IntUtil.toSignedHex(i),16) != i ) throw new RuntimeException(failMsg);            
        }
        for ( int i = 0; i < Integer.MAX_VALUE; i++ ){
            if ( Integer.parseInt(IntUtil.toSignedHex(i),16) != i ) throw new RuntimeException(failMsg);            
        }
        if ( Integer.parseInt(IntUtil.toSignedHex(Integer.MAX_VALUE),16) != Integer.MAX_VALUE ) throw new RuntimeException(failMsg);            
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
            
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== IntUtil.toSignedHex(int input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }

    public static void toStringAsUnsigned(){
        long start, end, result;
        final String failMsg = "IntUtil.toStringAsUnsigned() test fail.";
        System.out.println("=== IntUtil.toStringAsUnsigned(int input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between 0 and 4294967295.");
        int n = 0;
        for ( long i = 0L; i < 4294967296L; i++ ){
            if ( !IntUtil.toStringAsUnsigned(n).equals(""+i) ) throw new RuntimeException(failMsg);
            n = n + 1; 
        }
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
    
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== IntUtil.toStringAsUnsigned(int input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }

    public static void toLongAsUnsigned(){
        long start, end, result;
        final String failMsg = "IntUtil.toLongAsUnsigned() test fail.";
        System.out.println("=== IntUtil.toLongAsUnsigned(int input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Parsing all number between 0 and 4294967295.");
        int n = 0;
        for ( long i = 0L; i < 4294967296L; i++ ){
            if ( IntUtil.toLongAsUnsigned(n) != i ) throw new RuntimeException(failMsg);
            n = n + 1; 
        }
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
    
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== IntUtil.toLongAsUnsigned(int input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }
    
     public static void isOdd(){
        long start, end, result;
        final String failMsg = "IntUtil.isOdd() test fail.";
        System.out.println("=== IntUtil.isOdd(int input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Testing 5 numbers.");
    
        if ( IntUtil.isOdd(-255) != true ) throw new RuntimeException(failMsg); 
        if ( IntUtil.isOdd(-874) != false ) throw new RuntimeException(failMsg);
        if ( IntUtil.isOdd(8741) != true ) throw new RuntimeException(failMsg);
        if ( IntUtil.isOdd(8970) != false ) throw new RuntimeException(failMsg);
        if ( IntUtil.isOdd(0) != false) throw new RuntimeException(failMsg);
        
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
    
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== IntUtil.isOdd(int input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }
    
    public static void isEven(){
        long start, end, result;
        final String failMsg = "IntUtil.isEven() test fail.";
        System.out.println("=== IntUtil.isEven(int input) Test ===");
        System.out.println("*** If there is error, an exception will be throw.");
        System.out.println();

        start = System.currentTimeMillis();
        System.out.println("TEST 1: Testing 5 numbers.");
    
        if ( IntUtil.isEven(-88788880) != true ) throw new RuntimeException(failMsg); 
        if ( IntUtil.isEven(-88871) != false ) throw new RuntimeException(failMsg);
        if ( IntUtil.isEven(850478) != true ) throw new RuntimeException(failMsg);
        if ( IntUtil.isEven(999879) != false ) throw new RuntimeException(failMsg);
        if ( IntUtil.isEven(0) != true ) throw new RuntimeException(failMsg);
        
        System.out.println("TEST 1 Passed");
        System.out.println();System.out.println();System.out.println();
    
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("=== IntUtil.isEven(int input) Test Completed ===");
        System.out.println("The test took " + result + " ms to run.");
        System.out.println();
    }
    
}
