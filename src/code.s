.add:


.main:
    read r0, sp
    sub sp, sp, r0
    mov r0, sp
    read r1, sp
    sub sp, sp, r1
    mov r1, sp
    printhex r0, 32
    printhex r1, 32
