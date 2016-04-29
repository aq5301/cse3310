    .global main
    .func main
   
   
   
main:
    BL prompt
    BL scanint 
    MOV R5, R0
    MOV R0, #0
    MOV R6, #0 @ increment for outside loop of the sort
    B generate	

   
exit:   
    MOV R7, #1         
    SWI 0               


prompt:
    MOV R7, #4
    MOV R0, #1
    MOV R2, #18
    LDR R1, =prompt_str
    SWI 0
    MOV PC, LR

read_arrays:
    CMP R0, #20 @R0 is index
    BEQ exit
    
    LDR R1, =a
    LSL R2, R0, #2
    ADD R2, R1, R2
    LDR R3, =b
    LSL R4, R0, #2
    ADD R4, R3, R4
    
    LDR R1, [R2] @ a value
    LDR R3, [R4] @ b value
    
    PUSH {R0}
    PUSH {R1}
    PUSH {R2}
    PUSH {R3}
    
    MOV R2, R1
    MOV R1, R0
    
    BL print
    
    POP {R3}
    POP {R2}
    POP {R1}
    POP {R0}
    
    ADD R0, R0, #1
    
    
    B read_arrays
    
_printf:
    PUSH {LR}               @ store the return address
    LDR R0, =printf_str     @ R0 contains formatted string address
    BL printf               @ call printf
    POP {PC}   

print:
    PUSH {LR}
    LDR R0, =print_str
    BL printf
    POP {PC}

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
    
generate:
    CMP R0, #20 
    MOVEQ R0, #0
    BEQ readloop
    LDR R1, =a
    LSL R2, R0, #2
    ADD R2, R1, R2
    
    ADD R3, R5, R0
    STR R3, [R2]
    MOV R3, #0
    ADD R3, R5, R0
    ADD R4, R3, #1
    NEG R3, R4
    STR R3, [R2, #+1]
    
    ADD R0, R0, #2
    B generate


sort_ascending:
    CMP R6, #20
    MOVEQ R0, #0
    CMP R6, #20
    BEQ read_arrays
    
    LDR R1, =a
    LDR R3, =b
    LSL R2, R6, #2 @ for array a
    ADD R2, R1, R2
    LSL R4, R6, #2 @ for array b
    ADD R4, R3, R4
    
    ADD R7, R6, #1
    BL sort_ascendingInner
    STR R0, [R4] @ store value from 'sort_ascendingInner' into index i of array b
    
    ADD R6, R6, #1
    B sort_ascending


sort_ascendingInner:
    CMP R7, #20
    MOVEQ R0, R11
    CMP R7, #20
    MOVEQ PC, LR
    LSL R8, R7, #2 @ a
    ADD R8, R1, R8
    
    LDR R10, [R8] @ this is index + 1
    LDR R11, [R2] @ this is index
    CMP R11, R10
    MOVGE R11, R10

    ADD R7, R7, #1
    B sort_ascendingInner


scanint:
    MOV R4, LR              
    SUB SP, SP, #4          
    LDR R0, =num_str     
    MOV R1, SP              
    BL scanf                
    LDR R0, [SP]            
    ADD SP, SP, #4          
    MOV PC, R4  



.data


.balign 4
a:              .skip       80
b:              .skip       80
num_str:        .asciz      "%d"
print_str:      .asciz      "array_a[%d] = %d, array_b = %d\n"
prompt_str:     .ascii      "Enter an integer.\n"
printf_str:     .asciz      "a[%d] = %d\n"
