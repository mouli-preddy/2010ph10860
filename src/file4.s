.factorial:
	cmp r0, 1       
	beq .return
	bgt .continue
	b .return

.continue:
	sub sp, sp, 8   
	st r0, [sp]     
	st ra, 4[sp]    
	sub r0, r0, 1   
	call .factorial 
	ld r0, [sp]     
	ld ra, 4[sp]    
	mul r1, r0, r1  
	add sp, sp, 8   
	ret           
.return:
	mov r1, 1
	ret

.main:	
	mov r0, 10
	call .factorial

	.print r1
