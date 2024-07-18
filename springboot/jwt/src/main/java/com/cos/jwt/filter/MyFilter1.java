package com.cos.jwt.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 토큰을 만들었다고 가정
        // 토큰 : cos 이걸 만들어줘야 함. id, pw 정상적으로 들어와서 로그인이 완료 되면 토큰을 만들어주고 그걸 응답을 해준다.
        // 요청할 때마다 header의 Authorization 값으로 토큰을 가지고 온다
        // 그때 넘어오는 토큰이 내가 만든 토큰이 맞는지 검증만 하면 됨 (RSA, HS256)
        if (req.getMethod().equals("POST")) {
            System.out.println("POST 요청됨");
            String headAuth = req.getHeader("Authorization");
            System.out.println(headAuth);
            System.out.println("필터1");

            if(headAuth.equals("cos")) {
                chain.doFilter(req, res);
            } else {
                PrintWriter out = res.getWriter();
                out.println("인증 안됨");
            }
        }





    }
}
