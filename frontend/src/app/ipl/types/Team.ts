
export class Team {
  teamId : number;
  teamName : string;
  location : string;
  ownerName : string;
  establishmentYear :number;

  constructor(teamId:number,teamName : string,location : string,ownerName : string,establishmentYear :number)
  {
    this.teamId = teamId;
    this.teamName = teamName;
    this.location = location;
    this.ownerName= ownerName;
    this.establishmentYear = establishmentYear;
  }
  displayInfo():void{
    console.log(`TeamID: ${this.teamId}, TeamName: ${this.teamName}, Location: ${this.location}`);
  }
}