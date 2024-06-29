import { ApiProperty } from '@nestjs/swagger';
import { IsJSON, IsString } from 'class-validator';
import { Transform } from 'class-transformer';

export class SyncIdentityDto {
  @ApiProperty()
  @IsString()
  type: string;

  @ApiProperty()
  @IsString()
  userId: string;

  @ApiProperty()
  @IsString()
  @Transform(({ value }) => {
    return JSON.parse(value);
  })
  details: any;
}
