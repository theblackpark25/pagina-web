document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('registroForm');
  const btn = document.getElementById('btnRegistrar');
  const feedback = document.getElementById('feedback');

  const csrfToken = document.querySelector('meta[name="_csrf"]')?.content;
  const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.content || 'X-CSRF-TOKEN';

  btn.addEventListener('click', async () => {
    feedback.textContent = '';

    const payload = {
      dni: document.getElementById('dni').value.trim(),
      nombres: document.getElementById('nombres').value.trim(),
      apellidos: document.getElementById('apellidos').value.trim(),
      correo: document.getElementById('correo').value.trim(),
      contrasena: document.getElementById('contrasena').value
    };

    if (!/^\d{8}$/.test(payload.dni)) { feedback.textContent = 'DNI inválido'; return; }
    if (!payload.nombres || !payload.apellidos) { feedback.textContent = 'Completa nombres/apellidos'; return; }
    if (!/.+@.+\..+/.test(payload.correo)) { feedback.textContent = 'Correo inválido'; return; }
    if (payload.contrasena.length < 8) { feedback.textContent = 'Contraseña muy corta'; return; }

    try {
      btn.disabled = true;
      btn.textContent = 'Guardando...';

      const res = await fetch('/api/clientes', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          ...(csrfToken ? { [csrfHeader]: csrfToken } : {})
        },
        body: JSON.stringify(payload)
      });

      if (res.ok) {
        feedback.style.color = 'green';
        feedback.textContent = 'Cliente guardado correctamente.';
        form.reset();
      } else {
        const errText = await res.text();
        feedback.style.color = 'red';
        feedback.textContent = `Error: ${res.status} ${errText}`;
      }
    } catch (err) {
      console.error(err);
      feedback.style.color = 'red';
      feedback.textContent = 'Error al conectar con el servidor.';
    } finally {
      btn.disabled = false;
      btn.textContent = 'Guardar';
    }
  });
});
