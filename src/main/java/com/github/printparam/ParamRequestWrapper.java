package com.github.printparam;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class ParamRequestWrapper extends HttpServletRequestWrapper {
    private final String body;


    public ParamRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = streamToString(request.getInputStream());
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return inputStream.read();
            }
        };
    }

    private String streamToString(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        reader.close();
        return sb.toString();
    }
}
