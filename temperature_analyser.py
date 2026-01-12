# The temperature_analyzer script implements the following requirements:
# 1/ The system should accept temperature readings in Celsius or Fahrenheit.
# 2/ The system should convert the temperature to Celsius for analysis (if provided in Fahrenheit).
# 3/ The system should classify the patient's status based on body temperature and age-dependent thresholds. 
# 4/ The system should support displaying the status (if enabled) and logging it to a file (if enabled).


last_status = None
temperature_cache = None
unit_used_globally = "C"
debug_enabled = False

LOW_FEVER_THRESHOLD = 38
HIGH_FEVER_THRESHOLD = 39.4
BABY_FEVER_THRESHOLD = 37.4


def convert_to_celsius(temperature, scale="C"):
    global temperature_cache

    if temperature < -100 or temperature > 300:
        print("Warning: Unrealistic temperature detected.")

    if scale == "F":
        celsius = (temperature - 32) * 5/9
    else:
        celsius = temperature

    temperature_cache = celsius
    return celsius


def has_fever(temperature, scale="C"):
    try:
        celsius = convert_to_celsius(temperature, scale)
        fever = celsius > LOW_FEVER_THRESHOLD
        return fever
    except (TypeError, ValueError):
        return None


def analyze_patient(temperature, age, verbose=0, emergency_mode=False, log=False, scale="C"):
    global last_status

    if emergency_mode and temperature < 30:
        last_status = "HYPOTHERMIA?"
        return last_status

    temperature = convert_to_celsius(temperature, scale)

    if age < 3:
        threshold = BABY_FEVER_THRESHOLD
    else:
        threshold = HIGH_FEVER_THRESHOLD

    if temperature > threshold:
        status = "FEVER"
    else:
        if verbose > 2 and temperature > threshold - 0.2:
            status = "ALMOST FEVER"
        else:
            status = "NORMAL"

    last_status = status

    if log:
        try:
            with open("temp_log.txt", "a") as f:
                f.write(f"TEMP={temperature}, AGE={age}, STATUS={status}\n")
        except IOError:
            pass

    return status


def get_status_report(include_temperature=False, format="long"):
    global last_status, temperature_cache

    if include_temperature:
        return {"status": last_status, "temperature_cache": temperature_cache}
    elif format == "code":
        return last_status[:2] if last_status else None
    else:
        return f"Current status: {last_status}"


if __name__ == "__main__":

    temperature = 109.7
    age = 5

    print("Analyzing temperature:", temperature)

    result = analyze_patient(
        temperature,
        age,
        verbose=3,
        emergency_mode=False,
        log=True
    )

    print("Result:", result)
    print("Last status:", last_status)
    print("Status report:", get_status_report(include_temperature=True))