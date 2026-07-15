package br.org.edu.ifrn.lojacarro.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private static final int LIMITE = 10;
    private static final long TEMPO = 60000;

    private final Map<String, Integer> contador = new ConcurrentHashMap<>();
    private final Map<String, Long> ultimoAcesso = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String ip = request.getRemoteAddr();

        long agora = System.currentTimeMillis();

        ultimoAcesso.putIfAbsent(ip, agora);

        if (agora - ultimoAcesso.get(ip) > TEMPO) {
            contador.put(ip, 0);
            ultimoAcesso.put(ip, agora);
        }

        contador.put(ip, contador.getOrDefault(ip, 0) + 1);

        if (contador.get(ip) > LIMITE) {
            response.setStatus(429);
            response.getWriter().write("Muitas requisições. Aguarde 1 minuto.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}