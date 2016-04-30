    .global main
    .func main
   
   
   
main:
    BL prompt
    BL scanint
    MOV R5, R0
    MOV R0, #0


generate:
    CMP R0, #20 
    MOVEQ R0, #0
    BEQ readloop
    LDR R1, =a
    LSL R2, R0, #2
    ADD R2, R1, R2
    
  @  ADD R3, R5, R0
    MOV R3, #3
    STR R3, [R2]
    @MOV R3, #0
    ADD R3, R5, R0
    @MOV R4, #0
    @ADD R4, R3, #1
    @NEG R3, R4
    MOV R4, #3
    STR R4, [R2, #+1]
    
    ADD R0, R0, #2
    B generate
    
readloop:
    CMP R0, #20            @ check to see if we are done iterating
    BEQ exit          @ exit loop if done
    LDR R1, =a              @ get address of a
    LSL R2, R0, #2          @ multiply index*4 to get array offset
    ADD R2, R1, R2          @ R2 now has the element address
    LDR R1, [R2]            @ read the array at address 
    PUSH {R0}               @ backup register before printf
    PUSH {R1}               @ backup register before printf
    PUSH {R2}               @ backup register before printf
    MOV R2, R1              @ move array value to R2 for printf
    MOV R1, R0              @ move array index to R1 for printf
    BL  _printf             @ branch to print procedure with return
    POP {R2}                @ restore register
    POP {R1}                @ restore register
    POP {R0}                @ restore register
    ADD R0, R0, #1          @ increment index
    B   readloop            @ branch to next loop iteration    
    
prompt:
    MOV R7, #4
    MOV R0, #1
    MOV R2, #18
    LDR R1, =prompt_str
    SWI 0
    MOV PC, LR
   
scanint:
    MOV R4, LR              
    SUB SP, SP, #4          
    LDR R0, =num_str     
    MOV R1, SP              
    BL scanf                
    LDR R0, [SP]            
    ADD SP, SP, #4          
    MOV PC, R4  

exit:   
    MOV R7, #1         
    SWI 0               
    
_printf:
    PUSH {LR}               @ store the return address
    LDR R0, =printf_str     @ R0 contains formatted string address
    BL printf               @ call printf
    POP {PC}   



.data


.balign 4
a:              .skip       80
b:              .skip       80
num_str:        .asciz      "%d"
print_str:      .asciz      "array_a[%d] = %d, array_b = %d\n"
prompt_str:     .ascii      "Enter an integer.\n"
printf_str:     .asciz      "a[%d] = %d\n"
