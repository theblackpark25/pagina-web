document.addEventListener('DOMContentLoaded', () => {
        console.log('registro.js cargado');
        const btnVerificar = document.getElementById('btnVerificar');
        const dniInput = document.getElementById('dni');
        const nombresInput = document.getElementById('nombres');
        const apellidosInput = document.getElementById('apellidos');
        const feedback = document.getElementById('feedback');

        async function buscarNombrePorDni() {
          feedback.textContent = '';
          const dni = dniInput.value.trim();
          if (!/^\d{8}$/.test(dni)) {
            feedback.textContent = 'Introduce un DNI válido de 8 dígitos.';
            return;
          }

          // UX: bloquear botón y mostrar texto
          btnVerificar.disabled = true;
          const originalText = btnVerificar.textContent;
          btnVerificar.textContent = 'Consultando...';

          try {
            const res = await fetch('https://api.consultasperu.com/api/v1/query', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify({
                token: 'bb22078befaa69aac1bcbac19efb3a122ba5d233a148bb041d320a4344a196c3',
                type_document: 'dni',
                document_number: dni,
              }),
            });

            if (!res.ok) {
              const txt = await res.text();
              throw new Error(`HTTP ${res.status}: ${txt}`);
            }

            const data = await res.json();
            console.log(data);
            console.log(data.name);
            console.log(data.surname);
            // Mapea según la respuesta real de la API
            nombresInput.value = data.data.name;
            apellidosInput.value = data.data.surname;

            feedback.style.color = '#080';
            feedback.textContent = 'Datos cargados correctamente.';
          } catch (err) {
            console.error(err);
            feedback.style.color = '#b00';
            feedback.textContent = 'No se pudo obtener la información. Revisa la consola.';
          } finally {
            btnVerificar.disabled = false;
            btnVerificar.textContent = originalText;
          }
        }

        // conectar handler al botón (sin usar onclick inline)
        btnVerificar.addEventListener('click', buscarNombrePorDni);

        // opcional: permitir Enter cuando el foco esté en el input DNI
        dniInput.addEventListener('keydown', (e) => {
          if (e.key === 'Enter') {
            e.preventDefault();
            buscarNombrePorDni();
          }
        });

        // opcional: exponer en window para depuración (no necesario)
        window.buscarNombrePorDni = buscarNombrePorDni;
      });