import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AuthService } from "../../services/auth.service";
import { Router } from "@angular/router";
import { catchError, of, tap } from "rxjs";

@Component({
    selector:'app-login',
    templateUrl:'./login.component.html',
    styleUrls:['./login.component.scss']
})
export class LoginComponent {
  
    username:string = '';
    password:string ='';

    successMessage : string ='';
    errorMessage : string ='';

    loginForm:FormGroup;

    constructor(private fb:FormBuilder,private authService:AuthService,private router:Router)
    {
        this.loginForm = this.fb.group({
            username:['',[Validators.required,Validators.pattern(/^[a-zA-Z0-9]+$/)]],
            password :['',[Validators.required,Validators.minLength(8)]]
        });
    }
    ngOnInit():void
    {
        
    }
   
    onSubmit():void
    {
        this.successMessage ='';
        this.errorMessage ='';

        // this.loginForm.markAllAsTouched();
        if(this.loginForm.valid)
        {
            this.username = this.loginForm.get('username')?.value;
            this.authService.login(this.loginForm.value).pipe(
                tap((response)=>{
                    console.log(response);
                    localStorage.setItem("token",response.token);
                    localStorage.setItem("role",response.role);
                    localStorage.setItem("user_id",response.user_id);
                    console.log(localStorage.getItem("role"));
                    this.router.navigate(["ipl"]);
                }),
                catchError((error:string)=>{
                    this.errorMessage = 'Invalid username or password';
                    console.log("Login error",error);
                    return of(null);    
                })
                ).subscribe();
        }
            
            // if(this.simulateBackendLoginError(this.username))
            // {
            //         this.errorMessage = "Invalid username or password.";
            //         return;
            // }
            // this.password = this.loginForm.get('password')?.value;
            // this.successMessage = "Login Successful";
        else{
            this.errorMessage = 'Please fill out all required fields correctly.'
        }
       

            
    }
    simulateBackendLoginError(username :string):boolean
    {
       return !!username;
    }

    get f()
    {
      return  this.loginForm.controls;
    }
}
