import { Component } from "@angular/core";
import { Match } from "../../types/Match";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Team } from "../../types/Team";
import { IplService } from "../../services/ipl.service";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
    selector:'app-matchcreate',
    templateUrl:'./matchcreate.component.html',
    styleUrls:['./matchcreate.component.scss']
})
export class MatchCreateComponent {
    
    match : Match;

    successMessage : string ;
    errorMessage : string;

    matchForm : FormGroup;

    teams:Team[];
    

    isSubmitted :boolean =false;

    constructor(private fb : FormBuilder,private iplService:IplService){}

    ngOnInit(): void {
        this.loadTeams();
        this.matchForm = this.fb.group({
            // matchId : ['',[Validators.required]],
            firstTeamId : ['',[Validators.required]],
            secondTeamId : ['',[Validators.required]],
            matchDate : ['',[Validators.required]],
            venue: ['',[Validators.required]],
            result : ['',[Validators.required]],
            status : ['',[Validators.required]],
            winnerTeamId : ['',[Validators.required]]

        });
    }
    loadTeams()
    {
        this.iplService.getAllTeams().subscribe((teams)=>{
            this.teams = teams
        });
    }
    get matchId()
    {
        return this.matchForm.get('matchId');
    }
    get firstTeamId()
    {
        return this.matchForm.get('firstTeamId');
    }
    get secondTeamId()
    {
        return this.matchForm.get('secondTeamId');
    }
    get matchDate()
    {
        return this.matchForm.get('matchDate');
    }
    get venue()
    {
        return this.matchForm.get('venue');
    }
    get result()
    {
        return this.matchForm.get('result');
    }
    get status()
    {
        return this.matchForm.get('status');
    }
    get winnerTeamId()
    {
        return this.matchForm.get('winnerTeamId');
    }

    onSubmit()
    {
        this.isSubmitted = true;
        if(this.matchForm.valid)
        {
            // this.match = {...this.matchForm.value} as Match;
            this.iplService.addMatch(this.matchForm.value).subscribe(
                (response:Match)=>{
                    this.match = response;
                    this.successMessage ='Match created successfully!';
                    this.errorMessage ='';
                    console.log('Match Created: ',this.match);
                    this.resetForm();
                },
                (error:HttpErrorResponse)=>{
                    this.handleError(error);
                }
            );
            
        }
        else{
            
            this.successMessage = '';
            this.errorMessage = "Please fill out all required fields correctly.";
        }
    }
    resetForm()
    {
        this.matchForm.reset({
            
            firstTeamId :1,
            secondTeamId:0,
            matchDate:'',
            venue:'',
            results:'',
            status:'',
            winnerTeamId:0
        });
        this.errorMessage = '';
        this.successMessage = '';
        this.isSubmitted = false;

    }
    private handleError(error : HttpErrorResponse):void {
        if(error.error instanceof ErrorEvent)
        {
            this.errorMessage = `Client-side error: ${error.error.message}`;
        }else{
            this.errorMessage = `Server-side error: ${error.status} ${error.message}`;
            if(error.status === 400)
                {
                    this.errorMessage = 'Bad request. Please check your input.';
                }
            }
            this.successMessage ='';
            console.log('An error occured:',this.errorMessage);
}
}
