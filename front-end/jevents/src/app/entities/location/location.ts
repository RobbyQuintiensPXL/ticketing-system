export class Location {

  id: number;
  buildingName: string;
  zipCode: number;
  city: string;
  address: string;
  country: string;
  ticketOffice: string;


  constructor(id: number, buildingName: string, zipCode: number, city: string, address: string, country: string, ticketOffice: string) {
    this.id = id;
    this.buildingName = buildingName;
    this.zipCode = zipCode;
    this.city = city;
    this.address = address;
    this.country = country;
    this.ticketOffice = ticketOffice;
  }
}
