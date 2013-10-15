printhex r0, 32
printhex r2, 32



.add:
    cmp r1, r3 //picking the largest possible integer.
    bgt .greater
    mov r5, r3
    b .after
    .greater:
    mov r5, r1


    .after: // adding these two numbers
    add r5, r5, 1
    sub sp, sp, r5
    mov r9, 0
    mov r6, 0
    .loop:
    ld r7, r0[r6]
    ld r8, r2[r6]
    mov r10, r9
    add r10, r10, r8
    .print r10
    add r10, r10, r7
    mod r9, r10, 0x100
    div r10, r10, 0x100
    st r10, r6[sp]
    add r6, r6, 1
    cmp r5, r6
    .print r6
    bgt .loop

    mov r0, sp
    mov r1, r5
    ret


.mainasdf:

    read r0, sp // reading the input and printing it.......
    sub sp, sp, r0
    mov r0, sp
    read r2, sp
    sub sp, sp, r2
    mov r2, sp

    mov r1, 32
    mov r3, 32
    call .add

    printhex r0, 32


.main:
  read r0, sp
