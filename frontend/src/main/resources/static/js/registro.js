document.addEventListener('DOMContentLoaded', () => {
  const btn = document.getElementById('btnRegistrar');
  const form = document.getElementById('registroForm');

  btn.addEventListener('click', () => {
    ['nombres','apellidos','correo','contrasena','dni'].forEach(id=>{
      const el = document.getElementById('err-' + id);
      if(el) el.textContent = '';
    });

    const nombres = document.getElementById('nombres').value.trim();
    const apellidos = document.getElementById('apellidos').value.trim();
    const correo = document.getElementById('correo').value.trim();
    const contrasena = document.getElementById('contrasena').value;
    const dni = document.getElementById('dni').value.trim();

    let valid = true;

    if (nombres.length < 2) { document.getElementById('err-nombres').textContent = 'Ingrese nombres válidos'; valid=false; }
    if (apellidos.length < 2) { document.getElementById('err-apellidos').textContent = 'Ingrese apellidos válidos'; valid=false; }
    if (!/^\S+@\S+\.\S+$/.test(correo)) { document.getElementById('err-correo').textContent = 'Correo inválido'; valid=false; }
    if (contrasena.length < 8) { document.getElementById('err-contrasena').textContent = 'Min 8 caracteres'; valid=false; }
    if (!/^\d{8}$/.test(dni)) { document.getElementById('err-dni').textContent = 'DNI debe tener 8 dígitos'; valid=false; }

    if (!valid) return;

    document.getElementById('msg-success').style.display = 'block';
    form.reset();
  });
});
