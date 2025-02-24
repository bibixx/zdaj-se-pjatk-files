var win = null;
var lastURL;

function popUp(URL, wpc, hpc) {
w = window.outerWidth;
h = window.outerHeight;
sw = screen.width;
sh = screen.height;
if (w == null) w = sw;
if (h == null) h = sh;
nw = w*(wpc/100);
nh = h*(hpc/100);
nx = (sw - nw)/2
ny = (sh - nh)/2
feat = "scrollbars=yes,resizable=yes,width="+nw+",height="+nh+",screenX="+nx+",screenY="+ny;
if (win != null)
 if (!win.closed) win.close(); 
win = open(URL, "Pomoc", feat);
win.focus()
}
