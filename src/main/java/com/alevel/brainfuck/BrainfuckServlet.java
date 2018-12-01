package com.alevel.brainfuck;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/convert")
public class BrainfuckServlet extends HttpServlet {

    private ObjectMapper objectMapper;
    private HikariDataSource dataSource;
    private Map<Character,Expression> mainmap;

    @Override
    public void init() {
        HikariConfig hikariConfig = new HikariConfig("/hikary.properties");
        dataSource = new HikariDataSource(hikariConfig);
        objectMapper = new ObjectMapper();
        mainmap = new HashMap<>();
        mainmap.put('>',new NextOperation());
        mainmap.put('<',new PrevOperation());
        mainmap.put('+',new IncOperation());
        mainmap.put('-',new DecOperation());
        mainmap.put('.',new OutOperation());
        mainmap.put(']',new RightbrOperation());
        mainmap.put('[',new LeftbrOperation());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String text = req.getParameter("text");
        System.out.println(text);
        String answer;
        try {
            Context context = new Context(text);
            char c;
            while ( context.getConvertiblepointer() < text.length()) {
                mainmap.get(text.charAt(context.getConvertiblepointer())).interpret(context);
                char m = context.getConvertiblepointer();
                context.setConvertiblepointer(++m);
            }
            answer = context.getOutput();
        } catch (Exception e) {
            resp.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
            return;
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json;charset=utf8");
        Map<String, String> responseData = Collections.singletonMap("answer", answer);
        objectMapper.writeValue(resp.getOutputStream(), responseData);
    }

    @Override
    public void destroy() {
        dataSource.close();
    }
}
