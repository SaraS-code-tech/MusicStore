import './css/style.scss';
import * as bootstrap from 'bootstrap';
import Swal from 'sweetalert2';
import $ from 'jquery';

$(() => {
    //const $menuIcon = $('.fa-bars');
    const $sidebarMenu = $('#sidebarMenu');

    if ($sidebarMenu.length) {
        const offcanvasInstance = bootstrap.Offcanvas.getOrCreateInstance($sidebarMenu[0]);

        $sidebarMenu.find('.btn-close').on('click', () => {
            offcanvasInstance.hide();
        });
    }
    
    /*const offcanvasInstance = new bootstrap.Offcanvas($sidebarMenu[0]);
    $sidebarMenu.find('.btn-close').on('click', () => offcanvasInstance.hide());*/

    // Click sulle categorie principali → apri sub-list
    $('#listaFiltri').on('click', '.list-group-item', (e) => {
        const $li = $(e.currentTarget);
        const type = $li.data('type');

        // Se ha già una sub-list → toggle
        if ($li.next().hasClass('sub-list')) {
            $li.next().slideToggle(200);
            $li.find('i.fa-angle-down').toggleClass('rotate'); // ruota freccia
            return;
        }

        // Chiamata AJAX per generare sub-list
        $.ajax({
            url: '/singleCategorie',
            method: 'GET',
            data: { type: type },
            success: (data) => {
                if (!data || data.length === 0) return; // niente sub-list da creare

                let html = '<ul class="list-group sub-list" style="display:none;">';
                data.forEach(item => {
                    html += `<li class="list-group-item sub-item">${item}</li>`;
                });
                html += '</ul>';

                $li.after(html);
                $li.next().slideDown(200);
                $li.find('i.fa-angle-down').addClass('rotate'); // ruota freccia
            },
            error: (err) => {
                Swal.fire({
                    icon: 'error',
                    title: 'Errore',
                    text: 'Errore durante il caricamento delle categorie.',
                });
            }
        });
    });

    // Click sulle sub-list → redirect al filtro
    $('#listaFiltri').on('click', '.sub-item', (e) => {
        const name = $(e.currentTarget).text().trim();
        window.location.href = '/category?name=' + encodeURIComponent(name);
    });

    $('#all-products').on('click', () => {
        console.log('Redirecting to all products');
        window.location.href = '/';
    });

    $('#searchBar').on('keypress', (e) => {
        if (e.which === 13) { // ENTER
            const q = $('#searchBar').val().trim();
            if (q.length > 0) {
                window.location.href = '/search?search=' + encodeURIComponent(q);
            }
        }
    });

    if (performance.getEntriesByType("navigation")[0].type === "reload") {
        if (window.location.pathname.startsWith('/search') ||
            window.location.pathname.startsWith('/category')) {
            window.location.href = '/';
        }
    }

    $('#cart').on('click', () => {
        window.location.href = '/cart';
    });

    $('#user').on('click', () => {
        window.location.href = '/user';
    });
});