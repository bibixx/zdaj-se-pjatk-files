parse arg src args
if src = '' then call Emsg "Error. No source file specified"
if stream(src, 'c', 'query exists') = '' then call Emsg 'Error. No source'

call rxfuncadd 'sysloadfuncs', 'rexxutil', 'sysloadfuncs'
call sysloadfuncs

line. = 0
fnlist. = ''
fnlist.0 = 0

ln = 0
do while lines(src) \= 0
   ln = ln + 1
   line = linein(src)
   if pos('[',line) \= 0 then line = substArray(line)
   if line \= '' & word(line, 1) = 'function' then line = substFunc(line, ln)
   line.ln = line
end
line.0 = ln

do k = 1 to fnlist.0
   do i = 1 to line.0
      p = pos(fnlist.k'(', line.i)
      if p \= 0 then do
         if  p = 1 | substr(line.i,1, p-1) = '' then do
            parse var line.i fn '(' args ')' rest
            line.i = 'call 'fn args rest
         end
      end
   end
end
call stream src, 'c', 'close'

out = SysTempFileName('rsrc$$$.???')


do i = 1 to line.0
 call lineout out, line.i
end
call stream out, 'c', 'close'

say copies('-', 72)
say 'Interpretacja programu 'src
say
'regina.exe 'out args

if stream(out,'c', 'query exists') \= '' then do
  r = SysFileDelete(out) 
end

exit


substFunc: procedure expose fnlist.
parse arg line, nl

parse var line 'function' name '(' args ')' 'expose' explist
if name = '' then return
fnlist.0 = fnlist.0 + 1
k = fnlist.0
fnlist.k = strip(name, 'B')
explist1 = '' 
if explist \= '' then do
   do while explist \= '' 
      parse var explist nam explist
      p = pos('[]', nam)
      if (p \= 0 & p = length(nam)-1) then 
         nam = substr(nam, 1, length(nam-2))||'.'
      explist1 = explist1 nam
   end  
end
 
line = name ': procedure '
if explist1 \= '' then line = line 'expose' explist1
line = line||';' 
if args \= ''  then line = line||'parse arg 'args';'
return line



substArray: procedure expose src out
parse arg line, ln
line = strip(line, 'B')
parse var line nam '[]' cos '=' drugie '{' lista '}'
if cos = '' & drugie = '' & lista \= ''  then do
  nam = strip(nam, 'B')
  i = 0
  do while lista \= ''
     i = i + 1
     parse var lista arg.i ',' lista
  end 
  line = ''
  do j = 1 to i
     line = line||nam'.'j'='arg.j';'
  end
/*  l = length(line)
  if substr(line,l,1) \= '}' then do
  do forever
     line = strip(linein(src));
     
     
*/   
   
return line  
end
line = changestr('[]',line, '.')
line = changestr('][',line, '.')
line = changestr('[',line, '.')
line = changestr(']', line, '')
return line

Emsg:
parse arg msg
say msg
exit    






