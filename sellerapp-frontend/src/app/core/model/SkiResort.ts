export interface SkiResort{
    id?: number;
    name: string;
    description?: string;
    country?: string;
    liftPrice?: number;
    gondolaPrice?: number;
    seasonStarts: Date;
    seasonEnds: Date;
    groupCount?: number;
    ticketDeposit?: number;
    capacity?: number;
    occupacyForDay?: Date;
	occupacyRate?: number;
}