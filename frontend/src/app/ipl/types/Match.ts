import { Team } from "./Team";

export class Match {
 
    matchId:number;
    firstTeam :Team;
    secondTeam:Team;
    matchDate:Date;
    venue:string;
    result:string;
    status:string;
    winnerTeam:Team;

   
    constructor(matchId:number,firstTeam:Team,secondTeam:Team,matchDate:Date,venue:string,result:string,status:string,
        winnerTeam:Team)
        {
            this.matchId = matchId;
            // this.firstTeamId = team1.teamId;
            this.firstTeam = firstTeam;
            this.secondTeam = secondTeam;
            // this.secondTeamId = team2.teamId;
            this.matchDate = matchDate;
            this.venue = venue;
            this.result = result;
            this.status = status;
            this.winnerTeam = winnerTeam;
            
        }
    
        displayInfo():void{
            console.log(`Match ID : ${this.matchId}, Match Date: ${this.matchDate}, Venue: ${this.venue}`);
        }
}