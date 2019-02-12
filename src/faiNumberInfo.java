package host.fai.lib.faiNumber;
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
 * <p>The <code>faiNumberInfo</code> class provides the version number 
 * and the package name for this package.</p>
 * 
 * @author  Khang Hoang Nguyen
 * 
 * @since  1.0.0.f
 **/
public  final class faiNumberInfo{
    private faiNumberInfo(){};
    private final int[] VERSION_NUMBER = {1, 0, 0, 0};
    /**
     * 
     * <p>Version number: Major.Minor.Security/BugFix.Build
     *
     * <p>Build: (b: Beta), (rc-N: Release Candidate No.), (f: Final).
     * 
     * <p>Take note that the official online web docs for any major
     * release version may always use the (n).0.0.f for that
     * version and its minor, security/bugfix releases.
     * 
     * <p>Major releases will add new feature(s) and/or
     * remove unnecessary API. Any deprecated API will not be removed
     * until at least 3 major versions after the major version that
     * first informed of the removal of the API. However, if it is
     * simply a name change, the API with the old name may just redirect
     * the call to a new name.
     * 
     * <p>Minor releases do not alter, add, nor remove any API. Minor
     * releases are for coding layout improvement, code improvement,
     * and/or code optimization.
     * 
     * <p>Bugfix/Security releases do not alter, add, nor remove any API.
     * Bugfix/Security releases are releases that will fix discovered
     * bug(s) or security loophole(s).</p>
     **/
    public final String VERSION = "1.0.0.f";
    
    /**
     * host.fai.lib.faiNumber.
     **/
    public final String PACKAGE_NAME = "host.fai.lib.faiNumber";
    
    /**
     * <p>Return an array of int that represents the VERSION number.
     * <p>
     * [0] -&gt; Major<br/>
     * [1] -&gt; Minor<br/>
     * [2] -&gt; Security/BugFix<br/>
     * [3] -&gt; Build(-1: Beta, 0: Final, &gt;0: rc-N)</p>
     * 
     * @return  An array of int that represents the VERSION number.
     *
     * @since  1.0.0.f
     **/
    public final int[] VERSION_NUMBER(){
        return VERSION_NUMBER;
    }
}
