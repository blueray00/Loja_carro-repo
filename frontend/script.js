const API = "http://localhost:8080/carro";

const token = localStorage.getItem("jwt");

if (!token) {
    window.location.href = "login.html";
}

const headers = {
    "Authorization": "Bearer " + token,
    "Content-Type": "application/json"
};

const form = document.getElementById("carroForm");
const tabela = document.getElementById("tabelaCarros");
const mensagem = document.getElementById("mensagem");
const usuario = document.getElementById("usuarioLogado");

usuario.innerHTML = `<strong>${localStorage.getItem("usuario")}</strong>`;

document.getElementById("logout").onclick = () => {
    localStorage.clear();
    window.location.href = "login.html";
};

document.getElementById("cancelar").onclick = limparFormulario;

document.getElementById("recarregar").onclick = carregarCarros;

carregarCarros();

form.addEventListener("submit", salvarCarro);

async function carregarCarros() {

    try {

        const response = await fetch(API, {
            headers
        });

        if (response.status === 401 || response.status === 403) {
            logoutAutomatico();
            return;
        }

        const carros = await response.json();

        tabela.innerHTML = "";

        if (carros.length === 0) {

            tabela.innerHTML = `
                <tr>
                    <td colspan="4">
                        Nenhum carro cadastrado.
                    </td>
                </tr>
            `;

            return;
        }

        carros.forEach(carro => {

            tabela.innerHTML += `
                <tr>

                    <td>${carro.id}</td>

                    <td>${carro.modelo}</td>

                    <td>${carro.ano}</td>

                    <td>

                        <button
                            class="btn-editar"
                            onclick="editarCarro(${carro.id},'${carro.modelo}',${carro.ano})">

                            Editar

                        </button>

                        <button
                            class="btn-excluir"
                            onclick="excluirCarro(${carro.id})">

                            Excluir

                        </button>

                    </td>

                </tr>
            `;

        });

    } catch (e) {

        mostrarMensagem("Erro ao carregar os carros.", true);

    }

}

async function salvarCarro(e) {

    e.preventDefault();

    const id = document.getElementById("id").value;

    const carro = {

        modelo: document.getElementById("modelo").value.trim(),

        ano: Number(document.getElementById("ano").value)

    };

    let url = API + "/salvar";
    let metodo = "POST";

    if (id !== "") {

        url = API + "/" + id;
        metodo = "PUT";

        carro.id = Number(id);
    }

    try {

        const response = await fetch(url, {

            method: metodo,

            headers,

            body: JSON.stringify(carro)

        });

        if (response.status === 401 || response.status === 403) {
            logoutAutomatico();
            return;
        }

        if (!response.ok) {
            throw new Error();
        }

        mostrarMensagem("Carro salvo com sucesso.");

        limparFormulario();

        carregarCarros();

    } catch {

        mostrarMensagem("Erro ao salvar o carro.", true);

    }

}

function editarCarro(id, modelo, ano) {

    document.getElementById("id").value = id;
    document.getElementById("modelo").value = modelo;
    document.getElementById("ano").value = ano;

    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });

}

async function excluirCarro(id) {

    if (!confirm("Deseja realmente excluir este carro?")) {
        return;
    }

    try {

        const response = await fetch(API + "/" + id, {

            method: "DELETE",

            headers

        });

        if (response.status === 401 || response.status === 403) {
            logoutAutomatico();
            return;
        }

        mostrarMensagem("Carro excluído.");

        carregarCarros();

    } catch {

        mostrarMensagem("Erro ao excluir.", true);

    }

}

function limparFormulario() {

    form.reset();

    document.getElementById("id").value = "";

}

function mostrarMensagem(texto, erro = false) {

    mensagem.innerText = texto;

    mensagem.className = erro
        ? "mensagem erro-msg"
        : "mensagem sucesso-msg";

    setTimeout(() => {

        mensagem.innerHTML = "";

        mensagem.className = "mensagem";

    }, 3000);

}

function logoutAutomatico() {

    alert("Sua sessão expirou.");

    localStorage.clear();

    window.location.href = "login.html";

}