document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("ragForm");
    const questionInput = document.getElementById("questionInput");
    const answerBox = document.getElementById("answerBox");
    const sourcesList = document.getElementById("sourcesList");
    const chunksBox = document.getElementById("chunksBox");
    const loading = document.getElementById("loading");
    const suggestions = document.querySelectorAll(".suggestion");

    async function ask(question) {
        loading.classList.remove("d-none");
        answerBox.textContent = "";
        sourcesList.innerHTML = "";
        chunksBox.innerHTML = "";

        try {
            const response = await fetch("/api/rag/ask", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ question: question })
            });

            const data = await response.json();

            answerBox.textContent = data.answer || "Sin respuesta.";

            if (data.sources && data.sources.length > 0) {
                data.sources.forEach(source => {
                    const li = document.createElement("li");
                    li.textContent = source;
                    sourcesList.appendChild(li);
                });
            } else {
                sourcesList.innerHTML = "<li>No se recuperaron fuentes.</li>";
            }

            if (data.retrievedChunks && data.retrievedChunks.length > 0) {
                data.retrievedChunks.forEach(chunk => {
                    const div = document.createElement("div");
                    div.className = "chunk-box";
                    div.textContent = chunk;
                    chunksBox.appendChild(div);
                });
            } else {
                chunksBox.innerHTML = "<p class='text-muted mb-0'>No se recuperaron fragmentos.</p>";
            }

        } catch (error) {
            answerBox.textContent = "Error al consultar el servicio RAG.";
        } finally {
            loading.classList.add("d-none");
        }
    }

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const question = questionInput.value.trim();

        if (!question) {
            answerBox.textContent = "Escribe una pregunta antes de consultar.";
            return;
        }

        ask(question);
    });

    suggestions.forEach(button => {
        button.addEventListener("click", function () {
            questionInput.value = button.textContent.trim();
            ask(questionInput.value);
        });
    });
});