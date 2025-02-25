/* Instalacja REXX-a i PRX w systemie Windows */

say 'Instalacja REXXa i programu PRX.'
say ''
say 'Podaj katalog znajduj�cy si� na �cie�ce PATH (samo ENTER = c:\windows):'
dir = linein()
if dir = '' then dir = 'c:\windows'
dir = strip(dir, 'B')
if substr(dir, length(dir), 1) = '\' then dir = delstr(dir, length(dir), 1)
path = value('PATH',,'SYSTEM')
path = translate(path)
dir = translate(dir)
found = 0
do while path \= ''
   parse var path test ';' path
   if pos(dir, test) \= 0 then do
      found = 1
      leave
   end
end
if found = 0 then do
   say 'Uwaga!!! Katalog 'dir' nie znajduje si� na �cie�ce dost�pu'
   say '         zdefiniowanej w zmiennej �rodowiskowej PATH'
   say 'Mo�esz kontynuowa� instalacj�,' 
   say 'ale po�niej nale�y doda� katalog do scie�ki dost�pu,'
   say 'zmieniaj�c (np. w pliku AUTOEXEC.BAT) ustawienie PATH'
   say copies('-', 72)
   say 'Czy kontynuowa� instalacje (T = tak / N = nie) ? : '
   pull ans
   if ans \= 'T' then exit
end
say 'Pliki zostan� skopiowane do katalogu 'dir
say 'Czy dobrze (T = tak / N = nie) ? : '
pull ans
if ans \= 'T' then exit
'copy regina.exe  'dir'\*.*'
'copy prx.rexx  'dir'\*.*'
'copy *.dll  'dir'\*.*'


call rxfuncadd 'sysloadfuncs', 'rexxutil', 'sysloadfuncs'
call sysloadfuncs
out = dir'\prx.bat'
is = stream(out, 'C', 'QUERY EXISTS')
if (is \= '' ) then do
   r = SysFileDelete(out)
   if (r \= 0) then do
    say 'Nie mog� utworzy� pliku uruchomieniowego prx.bat.'
    say 'Instalacja moze nie byc poprawna.' 
    exit
  end 
end
call lineout out, '@echo off'
call lineout out, '@regina 'dir'\prx.rexx %1 %2 %3 %4 %5 %6 %7'
call stream out, 'C', 'CLOSE'
say 'Instalacja zako�czona.'
exit



   
  
