const API_URL = "http://localhost:8080";

const form = document.getElementById("loginForm");
const mensagemErro = document.getElementById("mensagemErro");

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    mensagemErro.textContent = "";

    const username = document.getElementById("usuario").value.trim();
    const password = document.getElementById("senha").value.trim();

    try {

        const response = await fetch(`${API_URL}/auth/login`, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                username,
                password
            })

        });

        const resposta = await response.text();

        if (!response.ok) {
            mensagemErro.textContent = resposta || "Usuário ou senha inválidos.";
            return;
        }

        localStorage.setItem("jwt", resposta);
        localStorage.setItem("usuario", username);

        window.location.href = "index.html";

    } catch (error) {

        console.error(error);

        mensagemErro.textContent =
            "Não foi possível conectar ao servidor.";

    }

});