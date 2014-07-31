/*
 * Copyright 2010-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin;

import org.junit.Test;

import java.io.File;

public class AntTaskJvmTest extends AntTaskBaseTest {
    private static final String JVM_OUT_FILE = "hello.jar";

    private void doJvmAntTest(String... extraJavaArgs) throws Exception {
        doAntTest(SUCCESSFUL, extraJavaArgs);

        String jar = getOutputFileByName(JVM_OUT_FILE).getAbsolutePath();

        runJava("hello.run", "-cp", jar + File.pathSeparator + getKotlinRuntimePath(), "hello.HelloPackage");
    }

    private static String getClassPathForAnt() {
        return getCompilerLib() + File.separator + "kotlin-ant.jar" + File.pathSeparator + getKotlinRuntimePath();
    }

    private static String getIdeaSdkHome() {
        return getKotlinProjectHome().getAbsolutePath() + File.separator + "ideaSDK";
    }

    @Test
    public void antTaskJvm() throws Exception {
        doJvmAntTest();
    }

    @Test
    public void antAdditionalArguments() throws Exception {
        doJvmAntTest();
    }

    @Test
    public void antWrongArguments() throws Exception {
        doAntTest(FAILED);
    }

    @Test
    public void jvmClasspath() throws Exception {
        doJvmAntTest();
    }

    @Test
    public void antTaskJvmManyRoots() throws Exception {
        doJvmAntTest();
    }

    @Test
    public void javacCompiler() throws Exception {
        doJvmAntTest("-cp", getClassPathForAnt(),
                     "-Dkotlin.home", getCompilerLib().getAbsolutePath());
    }

    @Test
    public void externalAnnotations() throws Exception {
        doJvmAntTest("-cp", getClassPathForAnt(),
                     "-Didea.sdk", getIdeaSdkHome(),
                     "-Dkotlin.home", getCompilerLib().getAbsolutePath());
    }

    @Test
    public void kotlinCompiler() throws Exception {
        doJvmAntTest("-cp", getClassPathForAnt(),
                     "-Didea.sdk", getIdeaSdkHome(),
                     "-Dkotlin.home", getCompilerLib().getAbsolutePath());
    }
}
