document.addEventListener('DOMContentLoaded', () => {
  const btnBoleta = document.getElementById('btnBoleta');
  const btnFactura = document.getElementById('btnFactura');
  const compModalEl = document.getElementById('compModal');
  const compModal = new bootstrap.Modal(compModalEl);
  const saveBtn = document.getElementById('saveComp');

  btnBoleta.addEventListener('click', ()=> {
    document.getElementById('tipoComp').value = 'Boleta';
    document.getElementById('modalTitle').textContent = 'Nueva Boleta';
    compModal.show();
  });
  btnFactura.addEventListener('click', ()=> {
    document.getElementById('tipoComp').value = 'Factura';
    document.getElementById('modalTitle').textContent = 'Nueva Factura';
    compModal.show();
  });

  saveBtn.addEventListener('click', ()=> {
    const cliente = document.getElementById('clienteComp').value.trim();
    const dni = document.getElementById('dniComp').value.trim();
    const total = document.getElementById('totalComp').value;

    if (!cliente || !/^\d{8}$/.test(dni) || Number(total) <= 0) {
      alert('Complete los campos correctamente (DNI 8 dígitos, total > 0)');
      return;
    }

    const tbl = document.getElementById('tblComprobantes').querySelector('tbody');
    const tipo = document.getElementById('tipoComp').value;
    const numero = (tipo === 'Boleta' ? 'B-' : 'F-') + Math.floor(Math.random()*900 + 100);
    const fecha = new Date().toISOString().slice(0,10);
    const tr = document.createElement('tr');
    tr.innerHTML = `<td>${tipo}</td><td>${numero}</td><td>${fecha}</td><td>${cliente}</td><td>S/ ${Number(total).toFixed(2)}</td>
      <td><button class="btn btn-sm btn-outline-secondary">Ver</button></td>`;
    tbl.prepend(tr);
    compModal.hide();
    document.getElementById('formComp').reset();
  });
});
