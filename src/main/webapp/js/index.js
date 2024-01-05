// slider js
const splide = new Splide( '#main-slider', {
    width: '80%',
    pagination: false,
} );

const thumbnails = document.getElementsByClassName( 'thumbnail' );
let current;

for ( let i = 0; i < thumbnails.length; i++ ) {
    initThumbnail( thumbnails[ i ], i );
}
