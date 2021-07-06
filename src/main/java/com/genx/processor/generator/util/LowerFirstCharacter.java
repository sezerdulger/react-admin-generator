package com.genx.processor.generator.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class LowerFirstCharacter implements TemplateDirectiveModel {

    public void execute(Environment env,
            Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body)
            throws TemplateException, IOException {
        // Check if no parameters were given:
        if (!params.isEmpty()) {
            throw new TemplateModelException(
                    "This directive doesn't allow parameters.");
        }
        if (loopVars.length != 0) {
                throw new TemplateModelException(
                    "This directive doesn't allow loop variables.");
        }
        
        // If there is non-empty nested content:
        if (body != null) {
            // Executes the nested body. Same as <#nested> in FTL, except
            // that we use our own writer instead of the current output writer.
            body.render(new LowerCaseFilterWriter(env.getOut()));
        } else {
            throw new RuntimeException("missing body");
        }
    }
    
    /**
     * A {@link Writer} that transforms the character stream to upper case
     * and forwards it to another {@link Writer}.
     */ 
    private static class LowerCaseFilterWriter extends Writer {
       
        private final Writer out;
           
        LowerCaseFilterWriter (Writer out) {
            this.out = out;
        }

        public void write(char[] cbuf, int off, int len)
                throws IOException {
//            char[] transformedCbuf = new char[len];
//            for (int i = 0; i < len; i++) {
//                transformedCbuf[i] = Character.toUpperCase(cbuf[i + off]);
//            }
//            out.write(transformedCbuf);
        	cbuf[0] = Character.toLowerCase(cbuf[0]);
        	out.write(String.valueOf(cbuf).trim());
        }

        public void flush() throws IOException {
            out.flush();
        }

        public void close() throws IOException {
            out.close();
        }
    }

}

