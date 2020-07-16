package com.atxzy.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import net.bytebuddy.implementation.bytecode.Throw;

import java.text.SimpleDateFormat;

public class ParseJwtTest {
    public static void main(String[] args) {
        try {
            Claims lingzun = Jwts.parser()
                    .setSigningKey("lingzun")
                    .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1OTQ2MTg2MjEsImV4cCI6MTU5NDYxODY4MSwicm9sZSI6ImFkaW4ifQ.0btcTGxRx-c7v4paN6Oj5lAB2ZQoEenbbbSmlavsXAA")
                    .getBody();
            System.out.println(lingzun.getId());
            System.out.println(lingzun.getSubject());
            System.out.println(new SimpleDateFormat().format(lingzun.getIssuedAt()));
            System.out.println(new SimpleDateFormat().format(lingzun.getExpiration())) ;
            System.out.println(lingzun.get("role"));
        }catch (ExpiredJwtException e){


        }

    }
}
