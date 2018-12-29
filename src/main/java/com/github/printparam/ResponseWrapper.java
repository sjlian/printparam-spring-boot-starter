package com.github.printparam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ResponseWrapper extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer;
    private ServletOutputStream out;
    private PrintWriter writer;

    ResponseWrapper(HttpServletResponse resp) throws IOException {
        super(resp);
        buffer = new ByteArrayOutputStream();
        out = new WrapperOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer, "UTF-8"));
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return out;
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    byte[] getResponseData() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

    private class WrapperOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bos;

        WrapperOutputStream(ByteArrayOutputStream stream) {
            bos = stream;
        }

        @Override
        public void write(int b) {
            bos.write(b);
        }

        @Override
        public void write(byte[] b) {
            bos.write(b, 0, b.length);
        }
    }
}
