out = '';

do forever
  txt = linein();
  if ( txt = "") then iterate;
  if ( txt = "quit") then leave;
  out = out txt;
end
say out; 