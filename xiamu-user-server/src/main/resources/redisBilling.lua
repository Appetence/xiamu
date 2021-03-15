local key = KEYS[1] --key
local num = tonumber( ARGV[1] ) --num

local current = tonumber( redis.call( 'get', key ) or "0" )

if current < num then
  return -1
else
  return redis.call( 'decrby', key, num )
end
