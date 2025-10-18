import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AuthService } from "../../services/auth.service";

@Component({
    selector:'app-registration',
    templateUrl:'./registration.component.html',
    styleUrls:['./registration.component.scss']
})
export class RegistrationComponent  {
    // Validators.pattern(/^[a-zA-Z0-9]+$/)]
    // (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).+$/)]



    successMessage : string ;
    errorMessage : string;
    // feedbackMessage :string;

    registrationForm:FormGroup;

    constructor(private fb:FormBuilder,private authService:AuthService)
    {
        this.registrationForm = this.fb.group({
            fullName:['',[Validators.required,Validators.pattern(/^[a-zA-Z ]+$/)]],
            username:['',[Validators.required,Validators.pattern(/^[a-zA-Z0-9]+$/)]],
            email:['',[Validators.required,Validators.email]],
            password:['',[Validators.required,Validators.pattern(/^(?=.*[A-Z])(?=.*\d).{8,}$/)]]
        });
    }

    ngOnInit():void
    {
        
    }
    onSubmit() :void{

        // this.successMessage = '';
        // this.errorMessage = '';

        // this.registrationForm.markAllAsTouched();
        // this.registrationForm.updateValueAndValidity();
        if(this.registrationForm.valid)
        {
            this.authService.createUser(this.registrationForm.value).subscribe(
                response =>{
                    this.successMessage='Registration successful!';
                    this.errorMessage = '';
                    console.log(this.registrationForm.value);
                    this.resetForm();
                },error =>{
                    this.errorMessage = error;
                    this.successMessage ='';
                }
            );
            
            
            
        }
        else{
            this.errorMessage='Please fill out all required fields correctly.';
            this.successMessage = '';
        }
    }
    resetForm()
    {
        this.registrationForm.reset();
    }
    get f()
    {
        return this.registrationForm.controls;
    }
}
