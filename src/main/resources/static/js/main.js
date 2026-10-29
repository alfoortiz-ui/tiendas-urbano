(() => {
    const menuBtn = document.getElementById('menu-toggle');
    const menu = document.getElementById('main-menu');
    const cartCount = document.getElementById('cart-count');
    const addCartButtons = document.querySelectorAll('.add-cart');
    const toast = document.getElementById('toast');
    const newsletterForm = document.getElementById('newsletter-form');
    const newsletterEmail = document.getElementById('newsletter-email');
    const newsletterFeedback = document.getElementById('newsletter-feedback');

    if (menuBtn && menu) {
        menuBtn.addEventListener('click', () => {
            menu.classList.toggle('open');
            const isOpen = menu.classList.contains('open');
            menuBtn.setAttribute('aria-expanded', String(isOpen));
        });
    }

    const showToast = (message) => {
        if (!toast) return;
        toast.textContent = message;
        toast.classList.add('show');
        window.setTimeout(() => toast.classList.remove('show'), 1800);
    };

    let count = Number(cartCount?.textContent || 0);
    addCartButtons.forEach((button) => {
        button.addEventListener('click', () => {
            count += 1;
            if (cartCount) {
                cartCount.textContent = String(count);
            }
            showToast('Producto agregado al carrito');
        });
    });

    if (newsletterForm && newsletterEmail && newsletterFeedback) {
        newsletterForm.addEventListener('submit', (event) => {
            event.preventDefault();
            const email = newsletterEmail.value.trim();
            const valid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);

            if (!valid) {
                newsletterFeedback.textContent = 'Ingresa un correo válido para continuar.';
                newsletterFeedback.style.color = '#7c1f44';
                newsletterEmail.setAttribute('aria-invalid', 'true');
                return;
            }

            newsletterFeedback.textContent = 'Suscripción demostrativa registrada correctamente.';
            newsletterFeedback.style.color = '#1f2a44';
            newsletterEmail.removeAttribute('aria-invalid');
            newsletterForm.reset();
        });
    }
})();
