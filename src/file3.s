.main:
mov r0, 10
mov r1, 1   
mov r2, r0  

.loop:
mul r1, r1, r2 
sub r2, r2, 1  
cmp r2, 1  
bgt .loop 

.print r1
