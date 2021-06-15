export interface SkiResort{
    id?: number;
    name?: string;
    description?: string;
    country?: string;
    liftPrice?: number;
    gondolaPrice?: number;
    seasonStart?: Date;
    seasonEnd?: Date;
    groupCount?: number;
    ticketDeposit?: number;
    capacity?: number;
    occupacyForDay?: Date;
	occupacyRate?: number;
}