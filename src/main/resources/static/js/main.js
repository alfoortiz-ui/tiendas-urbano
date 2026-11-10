(() => {
    const menuBtn = document.getElementById('menu-toggle');
    const menu = document.getElementById('main-menu');

    if (menuBtn && menu) {
        menuBtn.addEventListener('click', () => {
            menu.classList.toggle('open');
            const isOpen = menu.classList.contains('open');
            menuBtn.setAttribute('aria-expanded', String(isOpen));
        });
    }

})();
