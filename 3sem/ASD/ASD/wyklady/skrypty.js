var ff;

function napis(f, x) {
        ff=f;
          f.elements[0].blur(); 
          f.elements[0].value = x;
          setTimeout("stare()", 2000); 
   }

function stare() {
        ff.elements[0].value ="Zobacz odpowied¼";
}

function okno(ff) {
        window.open(ff, '', 'toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no,location=no,directories=no,top=20,left=20,fullscreen=no,height=200,width=250');
}

function okno1(ff) {
        window.open(ff, '', 'toolbar=no,menubar=no,scrollbars=no,resizable=no,status=no,location=no,directories=no,top=20,left=20,fullscreen=no,height=250,width=350');
}
